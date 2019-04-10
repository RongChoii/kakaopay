package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic1.SupplyBank;
import com.kakaopay.finance.model.file.SupplyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyDataRepository extends JpaRepository<SupplyData, String> {

    @Query(value = "select bank from supply_data group by bank", nativeQuery = true)
    public List<Object[]> findBankList();

    @Query(value = "select SUM(amount) as sum_amount, year from supply_data group by year, month", nativeQuery = true)
    public List<Integer> selectYearList();

    @Query(value = "select bank, sum(amount), year from supply_data where year = :year group by year", nativeQuery = true)
    public List<SupplyBank> selectAmountPerBankByYear(@Param("year") int year);
    //--> select bank, sum(amount) as sum_amount from supply_data where year= :year group by year, bank 로 수정해야 할듯 ?



}
