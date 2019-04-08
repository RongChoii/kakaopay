package com.kakaopay.finance.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="SUPPLY_DATA")
@IdClass(SupplyDataPK.class)
public class SupplyData {

    @Id
    @Column(name="YEAR")
    private int year;

    @Id
    @Column(name="MONTH")
    private int month;

    @Id
    @Column(name="BANK")
    private String bank;

    private int amount;

}
