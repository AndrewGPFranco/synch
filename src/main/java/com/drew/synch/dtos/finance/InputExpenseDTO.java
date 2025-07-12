package com.drew.synch.dtos.finance;

import com.drew.synch.enums.PaymentCategoryType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record InputExpenseDTO(
        @NotNull UUID idTable,
        @NotNull String name,
        @NotNull String month,
        @NotNull double amount,
        LocalDate paymentDate,
        @NotNull PaymentCategoryType paymentCategory,
        @NotNull LocalDate dueDate
) {}
