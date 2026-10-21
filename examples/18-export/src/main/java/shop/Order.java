package shop;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"id", "customer", "total"})
public record Order(String id, String customer, BigDecimal total) {
}
