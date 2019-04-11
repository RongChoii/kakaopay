package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic1.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {
    @Query(value = "select bank from supply_data group by bank", nativeQuery = true)
    public List<Bank> findBankList();
}
