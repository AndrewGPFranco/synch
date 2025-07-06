package com.drew.synch.dtos.user;

import com.drew.synch.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record UserOutputDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String fullname,
        @NotBlank String nickname,
        @NotNull LocalDate birthDate,
        @NotNull Set<RoleType> roles
) {
}
