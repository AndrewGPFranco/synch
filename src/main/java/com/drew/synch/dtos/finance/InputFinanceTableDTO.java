package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record InputFinanceTableDTO(
        @NotNull(message = "É obrigatório a tabela pertencer a um usuário") Long idOwner,
        @NotBlank(message = "É obrigatório informar um nome para a tabela!") String tableName,
        List<Long> users // Agora não mais not null, pois já tera o usuário proprietário
) {
}
