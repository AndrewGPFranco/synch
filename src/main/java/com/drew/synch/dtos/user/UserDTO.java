package com.drew.synch.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * DTO utilizado para trafegar o mínimo de dados referente ao usuário, para telas que não necessita de todas infos
 *
 * @author andrewgo
 */
@Builder
public record UserDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String nickname
) {
}
