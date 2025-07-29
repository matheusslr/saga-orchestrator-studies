package br.com.microservices.orchestrated.orchestratorservice.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETopics {
    START_SAGA("start-saga"),
    BASE_ORCHESTRATOR("orchestrator"),
    NOTIFY_ENDING("notify-ending"),
    FINISH_SUCCESS("finish-success"),
    FINISH_FAIL("finish-fail"),
    INVENTORY_SUCCESS("inventory-success"),
    INVENTORY_FAIL("inventory-fail"),
    PAYMENT_SUCCESS("payment-success"),
    PAYMENT_FAIL("payment-fail"),
    PRODUCT_VALIDATION_SUCCESS("product-validation-success"),
    PRODUCT_VALIDATION_FAIL("product-validation-fail");
    private String topic;
}
