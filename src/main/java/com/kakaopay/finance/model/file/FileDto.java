package com.kakaopay.finance.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="FILE_DTO")
public class FileDto {

    @Id
    @GeneratedValue
    @Column(name="sequence", nullable = false)
    private int sequence;

    private int year;
    private int month;
    private int molitFd;  // 주택도시기금
    private int kbBank;  // 국민은행
    private int wrBank;  // 우리은행
    private int shBank;  // 신한은행
    private int citiBank;  // 한국시티은행
    private int hnBank;  // 하나은행
    private int nhBank;  // 농협/수협은행
    private int kebBank;  // 외환은행
    private int etcBank;  // 기타은행


}