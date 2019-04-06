package com.kakaopay.finance.model.basic1;

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
public class SupplyListMain {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private List<SupplyList> list;

}