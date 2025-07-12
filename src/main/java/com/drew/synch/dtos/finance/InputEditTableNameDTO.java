package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record InputEditTableNameDTO(
        @NotNull UUID idTable,
        @NotBlank String newName
) {
}
