package com.drew.synch.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;

/**
 * DTO utilizado para trafegar o mínimo de dados referente ao usuário, para telas que não necessita de todas infos
 *
 * @author andrewgo
 */
@Builder
public record UserDTO(
        @NotBlank UUID id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String nickname
) {
}
