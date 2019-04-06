package com.kakaopay.finance.model.basic2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Entity
@Builder
@Data//lombok getter & setter & tostring
@AllArgsConstructor //생성자
@NoArgsConstructor
public class BestBank {

    @Id
    @GeneratedValue//자동 생성값..?...후... 모지...시퀀스같은거래.. 조사해보쟈 ~.~
    private int year;
    private String bank;


}