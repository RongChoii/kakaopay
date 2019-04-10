package com.kakaopay.finance.jpa;

import com.kakaopay.finance.model.basic3.YearAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface YearAmountRepository extends JpaRepository<YearAmount, String> {

    @Query(value = "select year, sum(amount) as amount from supply_data group by year order by year asc", nativeQuery = true)
    public List<YearAmount> selectYearTotal();

    @Query(value = "select bank, amount from supply_data where year = :year and bank = '외환은행'", nativeQuery = true)
    public List<YearAmount> selectBankAmountByYear(@Param("year") int year);
}
