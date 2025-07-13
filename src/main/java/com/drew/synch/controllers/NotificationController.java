package com.drew.synch.controllers;

import com.drew.synch.dtos.ResponseAPI;
import com.drew.synch.dtos.notification.InputNotificationAccessTableDTO;
import com.drew.synch.dtos.notification.OutputNotificationAccessTableDTO;
import com.drew.synch.entities.User;
import com.drew.synch.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/new/access-table")
    ResponseEntity<ResponseAPI> createNewNotification(@AuthenticationPrincipal User user,
                                                      @RequestBody InputNotificationAccessTableDTO dto) {
        dto.setUserOwner(user);
        notificationService.createNewNotification(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseAPI("Notificação criada!"));
    }

    @GetMapping("/check-contains-notifications")
    ResponseEntity<ResponseAPI> checkIfContainsNewNotifications(@AuthenticationPrincipal User user) {
        List<OutputNotificationAccessTableDTO> outputNotificationAccessTableDTOS = notificationService.checkIfContainsNewNotifications(user.getId());
        return ResponseEntity.ok(new ResponseAPI(outputNotificationAccessTableDTOS));
    }

    @PutMapping("/mark-as-read-by-user/{idNotification}")
    ResponseEntity<ResponseAPI> markAsReadByUser(@AuthenticationPrincipal User user, @PathVariable UUID idNotification) {
        notificationService.markAsReadByUser(user.getId(), idNotification);
        return ResponseEntity.ok(new ResponseAPI(null));
    }

}
