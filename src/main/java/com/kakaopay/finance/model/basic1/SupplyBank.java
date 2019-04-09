package com.kakaopay.finance.model.basic1;

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
public class SupplyBank {

    @Id
    @GeneratedValue
    private String bank;
    private int amount;

}