package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.file.SupplyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyDataRepository extends JpaRepository<SupplyData, String> {

}