package com.drew.synch.dtos.finance;

import lombok.Builder;

@Builder
public record InputExpenseDTO(
        Long idTable,
        String name,
        String month,
        double amount
) {}
