package com.kakaopay.finance.model.file;

import lombok.Data;

import java.io.Serializable;

@Data
public class SupplyDataPK implements Serializable {
    private int year;
    private int month;
    private String bank;
}
