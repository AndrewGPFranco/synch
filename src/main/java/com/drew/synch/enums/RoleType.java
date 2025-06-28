package com.drew.synch.enums;

import lombok.Getter;

@Getter
public enum RoleType {

    USER("Usuário"),
    ADMIN("Administrador"),
    MODERATOR("Moderador"),
    OWNER("Proprietário");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

}