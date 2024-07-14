package com.federicoariellotitto.personal_budget_service.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.federicoariellotitto.personal_budget_service.domain.Category;
import com.federicoariellotitto.personal_budget_service.domain.Type;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

public record CashFlowRequestDTO(
        @JsonProperty("amount")
        @NotNull(message = "Amount must be present")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,
        @JsonProperty("currency")
        @NotNull(message = "Currency must be present")
        Currency currency,
        @JsonProperty("description")
        String description,
        @JsonProperty("category")
        Category category,
        @JsonProperty("type")
        String type
) {
}
