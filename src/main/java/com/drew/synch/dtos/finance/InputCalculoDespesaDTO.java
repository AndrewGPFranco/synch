package com.drew.synch.dtos.finance;

import com.drew.synch.enums.OperacaoCalculoDespesa;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InputCalculoDespesaDTO(
        @NotNull UUID idTable,
        @NotNull OperacaoCalculoDespesa operacao
) {
}
