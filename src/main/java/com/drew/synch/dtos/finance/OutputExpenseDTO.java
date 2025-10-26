package com.drew.synch.dtos.finance;

import com.drew.synch.enums.PaymentCategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Builder
public record OutputExpenseDTO(
        @NotNull UUID idExpense,
        @NotNull String name,
        @NotNull Month month,
        @NotNull double amount,
        LocalDate paymentDate,
        @NotNull PaymentCategoryType paymentCategory,
        @NotNull LocalDate dueDate,
        String link
) {
}
