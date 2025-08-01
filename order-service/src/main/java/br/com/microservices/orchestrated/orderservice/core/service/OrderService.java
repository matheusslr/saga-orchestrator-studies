package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.core.document.Event;
import br.com.microservices.orchestrated.orderservice.core.document.Order;
import br.com.microservices.orchestrated.orderservice.core.dto.OrderRequest;
import br.com.microservices.orchestrated.orderservice.core.producer.SagaProducer;
import br.com.microservices.orchestrated.orderservice.core.repository.OrderRepository;
import br.com.microservices.orchestrated.orderservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {
    private static final String TRANSACTIONAL_ID_PATTERN = "%s_%s";

    private final OrderRepository repository;
    private final EventService eventService;
    private final SagaProducer producer;
    private final JsonUtil jsonUtil;

    public Order createOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .products(orderRequest.products())
                .createdAt(LocalDateTime.now())
                .transactionId(
                        String.format(TRANSACTIONAL_ID_PATTERN, Instant.now().toEpochMilli(), UUID.randomUUID())
                )
                .build();

        repository.save(order);
        producer.sendEvent(jsonUtil.toJson(order));
        return order;
    }

    private Event createPayload(Order order) {
        Event event = Event.builder()
                .orderId(order.getId())
                .transactionId(order.getTransactionId())
                .payload(order)
                .createdAt(LocalDateTime.now())
                .build();

        return eventService.save(event);
    }
}
