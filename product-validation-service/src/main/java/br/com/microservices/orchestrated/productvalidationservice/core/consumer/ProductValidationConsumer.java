package br.com.microservices.orchestrated.productvalidationservice.core.consumer;

import br.com.microservices.orchestrated.productvalidationservice.core.dto.Event;
import br.com.microservices.orchestrated.productvalidationservice.core.service.ProductValidationService;
import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@AllArgsConstructor
public class ProductValidationConsumer {
    private final JsonUtil jsonUtil;
    private final ProductValidationService productValidationService;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-success}"
    )
    public void consumeSuccessEvent(String payload) {
        log.info("Receiving success event {} from product-validation-success topic", payload);
        Event event = jsonUtil.toEvent(payload);
        productValidationService.validateExistingProduct(event);
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-fail}"
    )
    public void consumeFailEvent(String payload) {
        log.info("Receiving rollback event {} from product-validation-fail topic", payload);
        Event event = jsonUtil.toEvent(payload);
        productValidationService.rollbackEvent(event);
    }
}
