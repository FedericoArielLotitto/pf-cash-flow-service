package com.federicoariellotitto.personal_budget_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Entity
@Table(name = "cash_flow")
public class CashFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime creationDateTime;
    private Currency currency;
    private String description;
    private Tag tag;

}
