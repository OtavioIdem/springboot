package com.guarani.teste_backend.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.guarani.teste_backend.domain.enums.OrderStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total;

    private BigDecimal freightAmount;

    private BigDecimal discountAmount;

    @ManyToMany
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.AGUARDANDO_PAGAMENTO;
    }

    public void calculateTotal() {
        BigDecimal productsTotal = products.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Aplicando um valor fixo de frete e um desconto percentual
        BigDecimal freightFixed = new BigDecimal("15.00");
        BigDecimal discountPercentage = new BigDecimal("0.10"); // 10%

        BigDecimal discountValue = productsTotal.multiply(discountPercentage);

        this.freightAmount = freightFixed;
        this.discountAmount = discountValue;

        this.total = productsTotal.add(freightFixed).subtract(discountValue);
    }
}
    
