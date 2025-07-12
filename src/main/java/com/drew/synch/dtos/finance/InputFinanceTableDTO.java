package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record InputFinanceTableDTO(
        @NotBlank(message = "É obrigatório informar um nome para a tabela!") String tableName,
        List<UUID> users // Agora não mais not null, pois já tera o usuário proprietário
) {
}
