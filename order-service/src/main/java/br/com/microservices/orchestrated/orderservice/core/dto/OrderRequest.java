package br.com.microservices.orchestrated.orderservice.core.dto;

import br.com.microservices.orchestrated.orderservice.core.document.Product;

import java.util.List;

public record OrderRequest(List<Product> products) {
}
