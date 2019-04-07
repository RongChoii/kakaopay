package com.kakaopay.finance.model.basic3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YearAmount {

    @Id
    @GeneratedValue
    private int year;
    private int amount;

}
