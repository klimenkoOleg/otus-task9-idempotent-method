package com.oklimenko.payment.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oklimenko.payment.dto.ErrorResult;
import com.oklimenko.payment.exception.DuplicatedIdempotenceKeyValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ObjectMapper objectMapper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleException(Exception ex) {
        log.error("An unknown error occurred.", ex);
        return new ResponseEntity<>(ErrorResult.error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicatedIdempotenceKeyValidationException.class)
    public ResponseEntity<ErrorResult> handleException(DuplicatedIdempotenceKeyValidationException ex) {

        log.error("Duplicate payment = ", ex.getIdempotenceKey());
        return new ResponseEntity<>(ErrorResult.errorDuplicatePayment(ex.getIdempotenceKey()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}