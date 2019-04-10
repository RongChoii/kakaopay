package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic1.SupplyBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyBankRepository extends JpaRepository<SupplyBank, String> {

    @Query(value = "select bank, sum(amount) as sum_amount from supply_data where year = :year group by year, bank", nativeQuery = true)
    public List<SupplyBank> selectAmountPerBankByYear(@Param("year") int year);
}
