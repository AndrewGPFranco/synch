package com.drew.synch.mappers.notification;

import com.drew.synch.dtos.notification.NotificationAccessTableDTO;
import com.drew.synch.entities.NotificationAccessTable;
import com.drew.synch.facades.UserFacadeManagement;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class NotificationMapper {

    private final UserFacadeManagement userFacade;

    public NotificationAccessTable dtoToNotificationAccessTable(@NonNull NotificationAccessTableDTO dto) {
        return NotificationAccessTable.builder()
                .contentMessage(dto.getMessage())
                .createdAt(LocalDateTime.now())
                .userOwner(dto.getUserOwner())
                .users(userFacade.returningSetUsers(dto.getDestinationUsers()))
                .wasReadDestination(false)
                .wasExpired(false)
                .build();
    }

}
