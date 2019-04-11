package com.kakaopay.finance.model.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approximation {

    @Id
    @GeneratedValue
    private String bank;
    private int year;
    private int month;
    private int amount;
    private int year_count;

    public Approximation(String bank, int year, int month, int amount) {
        this.bank = bank;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

}