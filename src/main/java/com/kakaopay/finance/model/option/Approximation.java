package com.kakaopay.finance.model.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Approximation {

    @Id
    @GeneratedValue
    private int id;
    private String bank;
    private int year;
    private int month;
    private int amount;



}