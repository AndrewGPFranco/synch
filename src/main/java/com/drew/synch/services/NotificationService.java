package com.drew.synch.services;

import com.drew.synch.dtos.notification.NotificationAccessTableDTO;
import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.mappers.notification.NotificationMapper;
import com.drew.synch.repositories.NotificationAccessTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationMapper notificationMapper;
    private final NotificationAccessTableRepository notificationAccessTableRepository;

    public void createNewNotification(NotificationAccessTableDTO dto) {
        try {
            NotificationAccessTable entity = notificationMapper.dtoToNotificationAccessTable(dto);
            notificationAccessTableRepository.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao tentar enviar a notificação!");
        }
    }
}
