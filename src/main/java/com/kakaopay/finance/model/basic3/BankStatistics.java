package com.kakaopay.finance.model.basic3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatistics {

    @Id
    private String bank;
//    private List<YearName> support_amount111;
}
