package com.drew.synch.dtos.finance;

import lombok.Builder;

import java.time.Month;

@Builder
public record OutputExpenseDTO(
        String name,
        Month month,
        double amount
) {
}
