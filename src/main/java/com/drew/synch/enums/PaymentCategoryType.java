package com.drew.synch.enums;

import lombok.Getter;

@Getter
public enum PaymentCategoryType {

    FIXED("Valor Fixo"),
    VARIABLE("Valor Variável"),
    UNFORESEEN("Valor Imprevisto");

    private final String description;

    PaymentCategoryType(String description) {
        this.description = description;
    }

}
