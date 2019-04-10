package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.file.SupplyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyDataRepository extends JpaRepository<SupplyData, String> {

    @Query(value = "select bank from supply_data group by bank", nativeQuery = true)
    public List<Object[]> findBankList();

    @Query(value = "select year from supply_data group by year", nativeQuery = true)
    public List<Integer> selectYearList();








}
