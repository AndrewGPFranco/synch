package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AddUserInListDTO(
        @NotNull UUID idNotification,
        @NotNull boolean wasAccepted
) {
}
