package com.kakaopay.finance.model.basic3;

//import javax.persistence.Entity;
//import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.List;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatistics {

    @Id
    @GeneratedValue
    private String bank;
    private List<YearAmount> support_amount;


}
