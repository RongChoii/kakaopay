package com.kakaopay.finance.model.basic2;

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
public class BestBank {

    @Id
    @GeneratedValue
    private int year;
    private String bank;


}