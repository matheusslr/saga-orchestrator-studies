package br.com.microservices.orchestrated.productvalidationservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .message(e.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
