package com.drew.synch.enums;

import lombok.Getter;

@Getter
public enum NotificationType {
    ACCESS_TABLE("Acesso de Tabela", "Aceitar solicitação para acessar minha tabela!");

    private final String title;
    private final String messageContent;

    NotificationType(String title, String messageContent) {
        this.title = title;
        this.messageContent = messageContent;
    }
}
