package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.option.Approximation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApproximationRepository extends JpaRepository<Approximation, String> {
    @Query(value = "select sum(amount) as amount, count(year) as year_count " +
            "from supply_data " +
            "where bank = :bank " +
            "and month = :month " +
            "and year < 2018", nativeQuery = true)
    public Approximation selectApproximationAmountByBankAndMonth(
            @Param("bank") String bank
            , @Param("month") int month);

}