package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record InputFinanceTableDTO(
        @NotBlank(message = "É obrigatório informar um nome para a tabela!") String tableName,
        List<Long> users // Agora não mais not null, pois já tera o usuário proprietário
) {
}
