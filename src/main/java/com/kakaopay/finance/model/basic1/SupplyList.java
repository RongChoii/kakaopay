package com.kakaopay.finance.model.basic1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyList {

    @Id
    @GeneratedValue
    private String year;
    private int total_amount;
    private List<SupplyBank> detail_amount;


}