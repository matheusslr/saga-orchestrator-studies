package br.com.microservices.orchestrated.orderservice.config.exception;

import lombok.Builder;

@Builder
public record ExceptionDetails(String message, int status) {
}
