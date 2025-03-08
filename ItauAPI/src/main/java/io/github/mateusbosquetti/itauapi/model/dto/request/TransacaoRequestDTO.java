package io.github.mateusbosquetti.itauapi.model.dto.request;

import io.github.mateusbosquetti.itauapi.model.entity.Transacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record TransacaoRequestDTO(
        @NotNull @PositiveOrZero BigDecimal valor
) {
    public Transacao toEntity() {
        return Transacao.builder()
                .valor(this.valor)
                .build();
    }
}
