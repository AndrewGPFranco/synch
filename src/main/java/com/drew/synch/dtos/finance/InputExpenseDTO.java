package com.drew.synch.dtos.finance;

import lombok.Builder;

import java.util.UUID;

@Builder
public record InputExpenseDTO(
        UUID idTable,
        String name,
        String month,
        double amount
) {}
