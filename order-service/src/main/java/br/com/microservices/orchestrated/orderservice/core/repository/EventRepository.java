package br.com.microservices.orchestrated.orderservice.core.repository;

import br.com.microservices.orchestrated.orderservice.core.document.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findAllByOrderByCreatedAtDesc();

    Optional<Event> findFirstByOrderIdOrderByCreatedAtDesc(String orderId);

    Optional<Event> findFirstByTransactionIdOrderByCreatedAtDesc(String transactionId);
}
