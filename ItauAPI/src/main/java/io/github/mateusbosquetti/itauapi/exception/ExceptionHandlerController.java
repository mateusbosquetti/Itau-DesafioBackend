package io.github.mateusbosquetti.itauapi.exception;

import io.github.mateusbosquetti.itauapi.model.dto.response.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> genericHandler(Exception e) {
        return new ResponseEntity<>(
                new ExceptionResponseDTO(e.getMessage(), e.getClass(), Instant.now()), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(
                new ExceptionResponseDTO(e.getMessage(), e.getClass(), Instant.now()), HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

}
