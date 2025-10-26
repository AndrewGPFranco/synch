package com.drew.synch.services;

import com.drew.synch.dtos.finance.AddUserInListDTO;
import com.drew.synch.dtos.notification.InputNotificationAccessTableDTO;
import com.drew.synch.dtos.notification.OutputNotificationDTO;
import com.drew.synch.dtos.user.UserDTO;
import com.drew.synch.entities.FinanceTable;
import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.entities.NotificationAccessUser;
import com.drew.synch.entities.User;
import com.drew.synch.enums.NotificationType;
import com.drew.synch.exceptions.NotFoundException;
import com.drew.synch.mappers.notification.NotificationMapper;
import com.drew.synch.repositories.NotificationAccessTableRepository;
import com.drew.synch.repositories.NotificationAccessUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FinanceTableService financeService;
    private final NotificationMapper notificationMapper;
    private final NotificationAccessUserRepository notificationAccessUserRepository;
    private final NotificationAccessTableRepository notificationAccessTableRepository;

    public void createNewNotification(InputNotificationAccessTableDTO dto) {
        try {
            User usuarioQueEnviouConvite = dto.getUserOwner();

            List<FinanceTable> tabelasPropriasDoUsuario = financeService.getTabelasPropriasDoUsuario(usuarioQueEnviouConvite.getId());

            boolean isTabelaDoUsuario = tabelasPropriasDoUsuario.stream().anyMatch(t -> t.getId().equals(dto.getFinanceTableId()));

            if (!isTabelaDoUsuario)
                throw new RuntimeException("Essa tabela não te pertence, não é possível convidar outras pessoas!");

            String idUsuarioDestino = dto.getDestinationUser();
            boolean jaTemAcesso = financeService.verificaUsuarioJaTemAcesso(idUsuarioDestino, dto.getFinanceTableId());

            if (jaTemAcesso)
                throw new RuntimeException(String.format("O usuário com email %s já tem acesso a essa tabela!", dto.getDestinationUser()));

            NotificationAccessTable entity = notificationMapper.dtoToNotificationAccessTable(dto);

            entity.getNotificationUsers().forEach(user -> user.setNotification(entity));

            notificationAccessTableRepository.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    public Set<OutputNotificationDTO> checkIfContainsNewNotifications(UUID idUser) {
        try {
            Integer amount = notificationAccessTableRepository.checkIfContainsNewNotifications(idUser);

            if (amount > 0) {
                List<NotificationAccessTable> allNotifications = notificationAccessTableRepository.findAll();

                if (!allNotifications.isEmpty()) {
                    Set<OutputNotificationDTO> notificationUser = new HashSet<>();

                    for (NotificationAccessTable notification : allNotifications) {
                        boolean has = notification.listUsers().stream()
                                .anyMatch(user -> user.id().equals(idUser));
                        if (has) {
                            List<NotificationAccessUser> listNotificationNotRead = notification.getNotificationUsers();

                            for (NotificationAccessUser notificationAccessUser : listNotificationNotRead) {
                                if (notificationAccessUser.getNotification().equals(notification)) {
                                    notificationUser.add(OutputNotificationDTO.builder()
                                            .idNotification(notification.getId())
                                            .creatorUser(createUserDTOCreatorNotification(notification.getUserOwner()))
                                            .messageContent(notification.getContentMessage())
                                            .wasRead(notificationAccessUser.isWasRead())
                                            .notificationType(NotificationType.ACCESS_TABLE.getTitle())
                                            .createdAt(notification.getCreatedAt().toLocalDate())
                                            .wasAnswered(notificationAccessUser.isWasAnswered())
                                            .build());
                                }
                            }
                        }
                    }

                    return notificationUser;
                }
            }

            return new HashSet<>();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao tentar obter novas notificações!");
        }
    }

    private UserDTO createUserDTOCreatorNotification(User creatorUser) {
        return UserDTO.builder().name(creatorUser.getName()).nickname(creatorUser.getNickname()).build();
    }

    public void markAsReadByUser(UUID idUser, AddUserInListDTO dto) {
        try {
            NotificationAccessTable notificationAccessTable = notificationAccessTableRepository
                    .findById(dto.idNotification()).orElseThrow(() -> new NotFoundException("Erro ao encontrar notificação!"));

            if (dto.wasAccepted())
                addUserNotification(idUser, notificationAccessTable);

            notificationAccessUserRepository.markNotificationAsRead(idUser, dto.idNotification());
            notificationAccessUserRepository.markNotificationAsAnswered(idUser, dto.idNotification());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao atualizar a leitura da notificação!");
        }
    }

    private void addUserNotification(UUID idUser, NotificationAccessTable notification) {
        String contentMessage = notification.getContentMessage();

        if (contentMessage.equals(NotificationType.ACCESS_TABLE.getMessageContent()))
            financeService.addUserInList(idUser, notification.getIdFinanceTable());
    }

    public void markAllAsRead(UUID idUser) {
        try {
            notificationAccessUserRepository.markAllAsReadByUser(idUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao atualizar a leitura das notificações!");
        }
    }

    public void deleteAllNotifications(UUID idNotification, UUID idUser) {
        try {
            markAsExpiredNotification(idNotification, idUser);
            notificationAccessUserRepository.deleteAllByUser(idUser);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao remover todas as notificações!");
        }
    }

    public void deleteNotification(UUID idUser, UUID idNotification) {
        try {
            markAsExpiredNotification(idNotification, idUser);
            notificationAccessUserRepository.deleteNotificationByUser(idUser, idNotification);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao remover notificação!");
        }
    }

    public boolean checkIfItHasUserNotifications(UUID idNotification, UUID idUser) {
        return notificationAccessUserRepository.checkIfItHasUserNotifications(idNotification, idUser) > 0;
    }

    public void markAsExpiredNotification(UUID idNotification, UUID idUser) {
        if (!checkIfItHasUserNotifications(idNotification, idUser))
            notificationAccessTableRepository.markAsExpiredNotification(idNotification);
    }
}
