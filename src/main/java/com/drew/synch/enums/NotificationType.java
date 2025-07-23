package com.drew.synch.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    ACCESS_TABLE("Aceitar solicitação para acessar minha tabela!");

    private final String messageContent;

    NotificationType(String messageContent) {
        this.messageContent = messageContent;
    }
}
