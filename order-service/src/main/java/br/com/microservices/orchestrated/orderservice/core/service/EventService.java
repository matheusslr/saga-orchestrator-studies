package br.com.microservices.orchestrated.orderservice.core.service;

import br.com.microservices.orchestrated.orderservice.config.exception.ValidationException;
import br.com.microservices.orchestrated.orderservice.core.document.Event;
import br.com.microservices.orchestrated.orderservice.core.dto.EventFilters;
import br.com.microservices.orchestrated.orderservice.core.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class EventService {
    private EventRepository repository;

    public Event save(Event event) {
        return repository.save(event);
    }

    public void notifyEnding(Event event) {
        event.setOrderId(event.getOrderId());
        event.setCreatedAt(LocalDateTime.now());
        save(event);
        log.info("Order {} with saga notified! TransactionID: {}", event.getOrderId(), event.getTransactionId());
    }

    public List<Event> findAll() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public Event findByOrderId(String orderId) {
        return repository.findFirstByOrderIdOrderByCreatedAtDesc(orderId)
                .orElseThrow(() -> new ValidationException("Event not found by OrderId."));
    }

    public Event findByTransactionId(String transactionId) {
        return repository.findFirstByTransactionIdOrderByCreatedAtDesc(transactionId)
                .orElseThrow(() -> new ValidationException("Event not found by TransactionId."));
    }

    public Event findByFilters(EventFilters filters) {
        validateEmptyFilters(filters);
        if (!filters.orderId().isEmpty()) return findByOrderId(filters.orderId());
        return findByTransactionId(filters.transactionId());
    }

    private void validateEmptyFilters(EventFilters filters) {
        if (filters.orderId() == null && filters.transactionId() == null)
            throw new ValidationException("OrderId and TransactionId must be informed");
    }
}
