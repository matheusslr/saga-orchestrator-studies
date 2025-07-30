package br.com.microservices.orchestrated.orchestratorservice.config.exception;

import lombok.Builder;

@Builder
public record ExceptionDetails(String message, int status) {
}
