package com.federicoariellotitto.personal_budget_service.service;

import com.federicoariellotitto.personal_budget_service.domain.CashFlow;
import com.federicoariellotitto.personal_budget_service.exception.ResourceNotFoundException;
import com.federicoariellotitto.personal_budget_service.repository.CashFlowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CashFlowService {

    private final CashFlowRepository cashFlowRepository;
    public CashFlow create(CashFlow cashFlow) {
        validateCashFlowDoesNotExist(cashFlow);
        validatePositiveAmount(cashFlow);
        validateNotNullCreationDateTime(cashFlow);
        validatePresentCurrency(cashFlow);
        return cashFlowRepository.save(cashFlow);
    }

    public CashFlow update(CashFlow cashFlow) {
        validateCashFlowId(cashFlow.getId());
        validatePositiveAmount(cashFlow);
        validateNotNullCreationDateTime(cashFlow);
        validatePresentCurrency(cashFlow);
        validateCashFlowExist(cashFlow.getId());

        var cashFlowToUpdate = cashFlowRepository.findById(cashFlow.getId()).get();
        cashFlowToUpdate.setAmount(cashFlow.getAmount());
        cashFlowToUpdate.setType(cashFlow.getType());
        cashFlowToUpdate.setCurrency(cashFlow.getCurrency());
        cashFlowToUpdate.setDescription(cashFlow.getDescription());

        return cashFlowRepository.save(cashFlowToUpdate);
    }

    private void validateNotNullCreationDateTime(CashFlow cashFlow) {
        Assert.notNull(cashFlow.getCreationDateTime(), "CashFlow creationDateTime must be not null");
    }

    private void validatePositiveAmount(CashFlow cashFlow) {
        Assert.isTrue(null != cashFlow.getAmount() && cashFlow.getAmount().compareTo(BigDecimal.ZERO) > 0, "CashFlow amount must be positive");
    }

    private void validateCashFlowDoesNotExist(CashFlow cashFlow) {
        Assert.isNull(cashFlow.getId(), "CashFlow id must be null");
    }

    private void validatePresentCurrency(CashFlow cashFlow) {
        Assert.notNull(cashFlow.getCurrency(), "CashFlow currency must be present");
    }

    private void validateCashFlowExist(Long id) {
        if (!cashFlowRepository.existsById(id)) {
            throw new ResourceNotFoundException("CashFlow", "id", id.toString());
        }
        Assert.isTrue(cashFlowRepository.findById(id).isPresent(), "CashFlow does not exist");
    }

    private void validateCashFlowId(Long id) {
        Assert.notNull(id, "CashFlow id must not be null");
    }

    public void delete(Long id) {
        validateCashFlowExist(id);
        cashFlowRepository.deleteById(id);
    }
}
