package com.drew.synch.dtos.finance;

import lombok.Builder;

import java.time.Month;
import java.util.UUID;

@Builder
public record OutputExpenseDTO(
        UUID idExpense,
        String name,
        Month month,
        double amount
) {
}
