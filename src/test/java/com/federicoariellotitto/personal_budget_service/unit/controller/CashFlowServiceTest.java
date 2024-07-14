package com.federicoariellotitto.personal_budget_service.unit.controller;

import com.federicoariellotitto.personal_budget_service.builder.CashFlowBuilder;
import com.federicoariellotitto.personal_budget_service.domain.CashFlow;
import com.federicoariellotitto.personal_budget_service.repository.CashFlowRepository;
import com.federicoariellotitto.personal_budget_service.service.CashFlowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CashFlowServiceTest {

    @Autowired
    private CashFlowService cashFlowService;

    @MockBean
    private CashFlowRepository cashFlowRepository;

    @Test
    void create_givenCashFlowWithIdNotNull_shouldThrowException(){
        var cashFlowWithIdNotNull = CashFlowBuilder.builder()
                .complete()
                .withId(1L)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.create(cashFlowWithIdNotNull)
        ).withMessage("CashFlow id must be null");
    };

    @Test
    void create_givenANegativeAmount_shouldThrowException() {
        var cashFlowWithNegativeAmount = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .withAmount(BigDecimal.valueOf(-1))
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.create(cashFlowWithNegativeAmount)
        ).withMessage("CashFlow amount must be positive");
    }

    @Test
    void create_givenANullAmount_shouldThrowException() {
        var cashFlowWithNullAmount = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .withAmount(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.create(cashFlowWithNullAmount)
        ).withMessage("CashFlow amount must be positive");
    }

    @Test
    void create_givenAZeroAmount_shouldThrowException() {
        var cashFlowWithZeroAmount = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .withAmount(BigDecimal.ZERO)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.create(cashFlowWithZeroAmount)
        ).withMessage("CashFlow amount must be positive");
    }

    @Test
    void create_givenANullCreationDateTime_shouldThrowException() {
        var cashFlowWithNullCreationDateTime = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .withCreationDateTime(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.create(cashFlowWithNullCreationDateTime)
        ).withMessage("CashFlow creationDateTime must be not null");
    }

    @Test
    void create_givenANullCurrency_shouldThrowException() {
        var cashFlowWithNullCurrency = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .withCurrency(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.create(cashFlowWithNullCurrency)
        ).withMessage("CashFlow currency must be present");
    }

    @Test
    void create_givenACashFlowWithValidData_shouldReturnCashFlow() {
        var savedCashFlow = CashFlowBuilder.builder().complete().withId(1L).build();
        when(cashFlowRepository.save(any(CashFlow.class))).thenReturn(savedCashFlow);

        var cashFlowWithValidData = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .build();

        var cashFlow = cashFlowService.create(cashFlowWithValidData);

        assertThat(cashFlow).isNotNull();
        assertThat(cashFlow.getId()).isNotNull();
    }

    @Test
    void update_givenANotExistingCashFlow_shouldThrowException() {
        var savedCashFlow = CashFlowBuilder.builder().complete().withId(-1L).build();
        when(cashFlowRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        var cashFlowWithValidData = CashFlowBuilder.builder()
                .complete()
                .withId(savedCashFlow.getId())
                .build();

        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithValidData)
        ).withMessage("CashFlow does not exist");
    }

    @Test
    void update_givenCashFlowWithIdNull_shouldThrowException(){
        var cashFlowWithIdNull = CashFlowBuilder.builder()
                .complete()
                .withId(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithIdNull)
        ).withMessage("CashFlow id must not be null");
    };

    @Test
    void update_givenANegativeAmount_shouldThrowException() {
        var cashFlowWithNegativeAmount = CashFlowBuilder.builder()
                .complete()
                .withAmount(BigDecimal.valueOf(-1))
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithNegativeAmount)
        ).withMessage("CashFlow amount must be positive");
    }

    @Test
    void update_givenANullAmount_shouldThrowException() {
        var cashFlowWithNullAmount = CashFlowBuilder.builder()
                .complete()
                .withAmount(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithNullAmount)
        ).withMessage("CashFlow amount must be positive");
    }

    @Test
    void update_givenAZeroAmount_shouldThrowException() {
        var cashFlowWithZeroAmount = CashFlowBuilder.builder()
                .complete()
                .withAmount(BigDecimal.ZERO)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithZeroAmount)
        ).withMessage("CashFlow amount must be positive");
    }

    @Test
    void update_givenANullCreationDateTime_shouldThrowException() {
        var cashFlowWithNullCreationDateTime = CashFlowBuilder.builder()
                .complete()
                .withCreationDateTime(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithNullCreationDateTime)
        ).withMessage("CashFlow creationDateTime must be not null");
    }

    @Test
    void update_givenANullCurrency_shouldThrowException() {
        var cashFlowWithNullCurrency = CashFlowBuilder.builder()
                .complete()
                .withCurrency(null)
                .build();
        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.update(cashFlowWithNullCurrency)
        ).withMessage("CashFlow currency must be present");
    }

    @Test
    void update_givenACashFlowWithValidData_shouldReturnCashFlow() {
        var savedCashFlow = CashFlowBuilder.builder().complete().withId(1L).build();
        when(cashFlowRepository.findById(any(Long.class))).thenReturn(Optional.of(savedCashFlow));
        when(cashFlowRepository.save(any(CashFlow.class))).thenReturn(savedCashFlow);

        var cashFlowWithValidData = CashFlowBuilder.builder()
                .complete()
                .build();

        var cashFlow = cashFlowService.update(cashFlowWithValidData);

        assertThat(cashFlow).isNotNull();
        assertThat(cashFlow.getId()).isNotNull();
    }

    @Test
    void delete_givenACashFlowThatDoesNotExist_shouldThrowException() {
        when(cashFlowRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThatIllegalArgumentException().isThrownBy(
                () -> cashFlowService.delete(-1L)
        ).withMessage("CashFlow does not exist");
    }

}
