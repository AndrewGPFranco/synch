package com.drew.synch.controllers;

import com.drew.synch.dtos.notification.NotificationAccessTableDTO;
import com.drew.synch.entities.User;
import com.drew.synch.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/new/access-table")
    ResponseEntity<?> createNewNotification(@AuthenticationPrincipal User user,
                                            @RequestBody NotificationAccessTableDTO dto) {
        dto.setUserOwner(user);
        notificationService.createNewNotification(dto);

        return ResponseEntity.ok().build();
    }

}
