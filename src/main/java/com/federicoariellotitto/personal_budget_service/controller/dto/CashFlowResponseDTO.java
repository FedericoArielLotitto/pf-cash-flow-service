package com.federicoariellotitto.personal_budget_service.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.federicoariellotitto.personal_budget_service.domain.Category;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Builder
public record CashFlowResponseDTO(
        @JsonProperty("id") Long id,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("creation_date") LocalDateTime creationDateTime,
        @JsonProperty("currency") Currency currency,
        @JsonProperty("description") String description,
        @JsonProperty("category") Category category,
        @JsonProperty("type") String type
) {
}
