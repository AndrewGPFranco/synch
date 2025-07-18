package com.drew.synch.controllers;

import com.drew.synch.dtos.ResponseAPI;
import com.drew.synch.dtos.finance.AddUserInListDTO;
import com.drew.synch.dtos.notification.InputNotificationAccessTableDTO;
import com.drew.synch.dtos.notification.OutputNotificationAccessTableDTO;
import com.drew.synch.entities.User;
import com.drew.synch.services.NotificationService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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
        Set<OutputNotificationAccessTableDTO> outputNotificationAccessTableDTOS = notificationService.checkIfContainsNewNotifications(user.getId());
        return ResponseEntity.ok(new ResponseAPI(outputNotificationAccessTableDTOS));
    }

    @PutMapping("/mark-as-read-by-user")
    void markAsReadByUser(@AuthenticationPrincipal User user, @RequestBody AddUserInListDTO dto) {
        notificationService.markAsReadByUser(user.getId(), dto);
    }

    @PutMapping("/mark-all-as-read")
    void markAllAsRead(@AuthenticationPrincipal User user) {
        notificationService.markAllAsRead(user.getId());
    }

    @DeleteMapping("/delete-all")
    void deleteAllNotifications(@AuthenticationPrincipal User user, @PathParam("idNotification") UUID idNotification) {
        notificationService.deleteAllNotifications(idNotification, user.getId());
    }

    @DeleteMapping("/delete-notification/{idNotification}")
    void deleteNotification(@AuthenticationPrincipal User user, @PathVariable UUID idNotification) {
        notificationService.deleteNotification(user.getId(), idNotification);
    }

}
