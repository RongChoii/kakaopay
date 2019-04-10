package com.kakaopay.finance.model.basic1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplyListTotal {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private List<SupplyList> list;

}