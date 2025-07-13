package com.drew.synch.dtos.notification;

import com.drew.synch.dtos.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record OutputNotificationAccessTableDTO(
        @NotNull UserDTO creatorUser,
        @NotBlank String messageContent
) {}
