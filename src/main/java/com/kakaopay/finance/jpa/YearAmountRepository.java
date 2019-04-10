package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic3.YearAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface YearAmountRepository extends JpaRepository<YearAmount, String> {

    @Query(value = "select year, sum(amount) as amount from supply_data group by year order by year asc", nativeQuery = true)
    public List<YearAmount> selectYearTotal();

    @Query(value = "select year, round(cast(sum(amount) as float)/12, 0) as amount from supply_data where bank='외환은행' group by year, bank", nativeQuery = true)
    public List<YearAmount> selectYearAmount();
}
