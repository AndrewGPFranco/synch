package com.drew.synch.dtos.user;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank(message = "Email obrigatório!") String email,
        @NotBlank(message = "Senha obrigatória") String password
) {
}

