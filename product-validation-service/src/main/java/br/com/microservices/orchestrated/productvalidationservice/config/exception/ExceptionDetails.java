package br.com.microservices.orchestrated.productvalidationservice.config.exception;

import lombok.Builder;

@Builder
public record ExceptionDetails(String message, int status) {
}
