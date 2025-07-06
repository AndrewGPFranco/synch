package com.drew.synch.dtos.finance;

import com.drew.synch.entities.Expense;
import com.drew.synch.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OutputFinanceTableDTO(
        @NotNull Long idTable,
        @NotBlank String tableName,
        @NotNull List<User> users,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt,
        @NotNull List<Expense> expenses
) {
}
