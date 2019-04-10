package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic2.BestBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BestBankRepository extends JpaRepository<BestBank, String> {
    @Query(value = "select year from supply_data group by year", nativeQuery = true)
    public List<Integer> selectYearList();

    @Query(value = "select year, bank, sum(amount) as amount from supply_data where year = :year group by bank, year", nativeQuery = true)
    public List<BestBank> selectAmountByYear(@Param("year") int year);

}
