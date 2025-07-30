package br.com.microservices.orchestrated.paymentservice.config.exception;

import lombok.Builder;

@Builder
public record ExceptionDetails(String message, int status) {
}
