package com.federicoariellotitto.personal_budget_service.controller;

import com.federicoariellotitto.personal_budget_service.controller.dto.CashFlowRequestDTO;
import com.federicoariellotitto.personal_budget_service.controller.dto.CashFlowResponseDTO;
import com.federicoariellotitto.personal_budget_service.domain.CashFlow;
import com.federicoariellotitto.personal_budget_service.domain.Type;
import com.federicoariellotitto.personal_budget_service.service.CashFlowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/cash-flows")
@RequiredArgsConstructor
public class CashFlowController {
    private final CashFlowService cashFlowService;

    @PostMapping
    public ResponseEntity<CashFlowResponseDTO> create(@Valid @RequestBody CashFlowRequestDTO cashFlowRequestDTO) {
        var persistedCashFlow = cashFlowService.create(toCashFlow(cashFlowRequestDTO));
        var response = CashFlowResponseDTO.builder()
                .id(persistedCashFlow.getId())
                .amount(persistedCashFlow.getAmount())
                .description(persistedCashFlow.getDescription())
                .currency(persistedCashFlow.getCurrency())
                .creationDateTime(persistedCashFlow.getCreationDateTime())
                .type(persistedCashFlow.getType())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CashFlowResponseDTO> put(@Valid @RequestBody CashFlowRequestDTO cashFlowRequestDTO) {
        var persistedCashFlow = cashFlowService.update(toCashFlow(cashFlowRequestDTO));
        var response = CashFlowResponseDTO.builder()
                .id(persistedCashFlow.getId())
                .amount(persistedCashFlow.getAmount())
                .description(persistedCashFlow.getDescription())
                .currency(persistedCashFlow.getCurrency())
                .creationDateTime(persistedCashFlow.getCreationDateTime())
                .type(persistedCashFlow.getType())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cashFlowService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CashFlow toCashFlow(CashFlowRequestDTO cashFlowRequestDTO) {
        var cashFlow = new CashFlow();
        cashFlow.setId(cashFlowRequestDTO.id());
        cashFlow.setCurrency(cashFlowRequestDTO.currency());
        cashFlow.setDescription(cashFlowRequestDTO.description());
        cashFlow.setAmount(cashFlowRequestDTO.amount());
        cashFlow.setCreationDateTime(LocalDateTime.now());
        //cashFlow.setType(Type.valueOf(cashFlowRequestDTO.type()));
        cashFlow.setType(cashFlowRequestDTO.type());
        return cashFlow;
    }
}
