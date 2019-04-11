package com.kakaopay.finance.model.basic1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyList {

    @Id
    @GeneratedValue
    private String year;
    private int total_amount;
    private Map<String, Object> detail_amount;

}