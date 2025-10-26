package com.drew.synch.mappers.notification;

import com.drew.synch.dtos.notification.InputNotificationAccessTableDTO;
import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.facades.UserFacadeManagement;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final UserFacadeManagement userFacade;

    public NotificationAccessTable dtoToNotificationAccessTable(@NonNull InputNotificationAccessTableDTO dto) {
        return NotificationAccessTable.builder()
                .contentMessage(dto.getNotificationType().getMessageContent())
                .createdAt(LocalDateTime.now())
                .userOwner(dto.getUserOwner())
                .notificationUsers(userFacade.returningListUsers(new HashSet<>(Collections.singletonList(dto.getDestinationUser()))))
                .wasExpired(false)
                .idFinanceTable(dto.getFinanceTableId())
                .build();
    }

}
