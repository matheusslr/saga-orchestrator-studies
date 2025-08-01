package br.com.microservices.orchestrated.orderservice.core.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "order")
public class Order {
    @Id
    private String id;
    private List<Product> products;
    private LocalDateTime createdAt;
    private String transactionId;
    private double totalAmount;
    private Integer totalItems;
}
