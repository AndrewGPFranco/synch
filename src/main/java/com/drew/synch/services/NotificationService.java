package com.drew.synch.services;

import com.drew.synch.dtos.notification.InputNotificationAccessTableDTO;
import com.drew.synch.dtos.notification.OutputNotificationAccessTableDTO;
import com.drew.synch.dtos.user.UserDTO;
import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.entities.User;
import com.drew.synch.mappers.notification.NotificationMapper;
import com.drew.synch.repositories.NotificationAccessTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationMapper notificationMapper;
    private final NotificationAccessTableRepository notificationAccessTableRepository;

    public void createNewNotification(InputNotificationAccessTableDTO dto) {
        try {
            NotificationAccessTable entity = notificationMapper.dtoToNotificationAccessTable(dto);

            entity.getUsers().forEach(user -> user.setNotification(entity));

            notificationAccessTableRepository.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao tentar enviar a notificação!");
        }
    }

    public List<OutputNotificationAccessTableDTO> checkIfContainsNewNotifications(UUID idUser) {
        try {
            Integer amount = notificationAccessTableRepository.checkIfContainsNewNotifications(idUser);

            if (amount > 0) {
                List<NotificationAccessTable> allNotifications = notificationAccessTableRepository.findAll();

                if (!allNotifications.isEmpty()) {
                    List<OutputNotificationAccessTableDTO> notificationUser = new ArrayList<>();

                    for (NotificationAccessTable notification : allNotifications) {
                        boolean has = notification.listUsers().stream()
                                .anyMatch(user -> user.id().equals(idUser));
                        // TODO: verificar se o usuário já leu a notificação
                        if (has && (!notification.wasExpired())) {
                            notificationUser.add(OutputNotificationAccessTableDTO.builder().creatorUser(
                                            createUserDTOCreatorNotification(notification.getUserOwner())
                                    )
                                    .messageContent(notification.getContentMessage())
                                    .build());
                        }
                    }

                    return notificationUser;
                }
            }

            return new ArrayList<>();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao tentar obter novas notificações!");
        }
    }

    private UserDTO createUserDTOCreatorNotification(User creatorUser) {
        return UserDTO.builder().name(creatorUser.getName()).nickname(creatorUser.getNickname()).build();
    }

    public void markAsReadByUser(UUID idUser, UUID idNotification) {

    }
}
