package com.drew.synch.dtos.finance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record InputEditTableNameDTO(
        @NotNull Long idTable,
        @NotBlank String newName
) {
}
