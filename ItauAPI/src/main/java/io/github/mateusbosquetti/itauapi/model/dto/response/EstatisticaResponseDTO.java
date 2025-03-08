package io.github.mateusbosquetti.itauapi.model.dto.response;

import java.math.BigDecimal;

public record EstatisticaResponseDTO(
        Integer count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max
) {
}
