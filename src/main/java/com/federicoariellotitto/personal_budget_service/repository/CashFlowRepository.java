package com.federicoariellotitto.personal_budget_service.repository;

import com.federicoariellotitto.personal_budget_service.domain.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {
}
