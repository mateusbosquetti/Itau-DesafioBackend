package io.github.mateusbosquetti.itauapi.model.dto.request;

import io.github.mateusbosquetti.itauapi.model.entity.Transacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequestDTO(
        @NotNull @PositiveOrZero BigDecimal valor
) {
    public Transacao toEntity() {
        return Transacao.builder()
                .valor(this.valor.setScale(2, BigDecimal.ROUND_HALF_UP))
                .dataHora(OffsetDateTime.now())
                .build();
    }
}
