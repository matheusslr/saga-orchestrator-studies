package br.com.microservices.orchestrated.inventoryservice.config.exception;

import lombok.Builder;

@Builder
public record ExceptionDetails(String message, int status) {
}
