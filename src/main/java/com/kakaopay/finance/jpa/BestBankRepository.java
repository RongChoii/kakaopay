package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic2.BestBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BestBankRepository extends JpaRepository<BestBank, String> {

    @Query(value = "SELECT A.YEAR, A.BANK  FROM SUPPLY_DATA A, (SELECT MAX(AMOUNT) AMT FROM SUPPLY_DATA) B WHERE A.AMOUNT = B.AMT ORDER BY A.YEAR DESC", nativeQuery = true)
    public BestBank selectBsetBank();
}
