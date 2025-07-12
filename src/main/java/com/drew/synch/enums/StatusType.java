package com.drew.synch.enums;

import lombok.Getter;

@Getter
public enum StatusType {

    COMPLETED("Concluído"),
    ONGOING("Em andamento"),
    TODO("A definir");

    private final String description;

    StatusType(String description) {
        this.description = description;
    }

}
