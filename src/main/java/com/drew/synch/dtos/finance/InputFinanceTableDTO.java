package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record InputFinanceTableDTO(
        @NotBlank(message = "É obrigatório informar um nome para a tabela!") String tableName,
        @NotNull(message = "É obrigatório colocar ao menos 1 usuário para a tabela!") List<Long> users
) {
}
