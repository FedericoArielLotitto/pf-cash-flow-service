package com.federicoariellotitto.personal_budget_service.builder;

import com.federicoariellotitto.personal_budget_service.domain.CashFlow;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

public class CashFlowBuilder {

    private CashFlow cashFlow;

    public CashFlowBuilder() {
        this.cashFlow = new CashFlow();
    }

    public static CashFlowBuilder builder() {
        return new CashFlowBuilder();
    }

    public CashFlowBuilder complete() {
        this.cashFlow.setId(1L);
        this.cashFlow.setAmount(BigDecimal.TEN);
        this.cashFlow.setCreationDateTime(LocalDateTime.now());
        this.cashFlow.setDescription(null);
        this.cashFlow.setCurrency(Currency.getInstance(Locale.getDefault()));
        return this;
    }

    public CashFlowBuilder toCreate() {
        this.complete();
        this.cashFlow.setId(null);
        return this;
    }
    public CashFlowBuilder withId(Long id) {
        this.cashFlow.setId(id);
        return this;
    }

    public CashFlowBuilder withAmount(BigDecimal amount) {
        this.cashFlow.setAmount(amount);
        return this;
    }
    public CashFlowBuilder withCreationDateTime(LocalDateTime creationDateTime) {
        this.cashFlow.setCreationDateTime(creationDateTime);
        return this;
    }

    public CashFlowBuilder withCurrency(Currency currency) {
        this.cashFlow.setCurrency(currency);
        return this;
    }

    public CashFlow build() {
        return this.cashFlow;
    }
}
