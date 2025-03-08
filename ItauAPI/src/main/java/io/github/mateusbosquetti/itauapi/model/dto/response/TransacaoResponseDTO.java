package io.github.mateusbosquetti.itauapi.model.dto.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoResponseDTO(
        BigDecimal valor,
        OffsetDateTime dataHora
) {
}
