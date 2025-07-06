package com.drew.synch.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserInputDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String fullname,
        @NotBlank String nickname,
        @NotBlank String password,
        @NotNull LocalDate birthDate
) {
}
