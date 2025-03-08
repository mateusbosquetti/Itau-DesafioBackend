package io.github.mateusbosquetti.itauapi.model.dto.response;

import java.time.Instant;

public record ExceptionResponseDTO(
        String message,
        Class aClass,
        Instant instant
) {
}
