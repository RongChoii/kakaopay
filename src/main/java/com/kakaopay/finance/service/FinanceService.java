package com.kakaopay.finance.service;

import com.kakaopay.finance.model.basic1.SupplyBank;
import com.kakaopay.finance.model.basic1.SupplyList;
import com.kakaopay.finance.model.basic1.SupplyListMain;
import com.kakaopay.finance.model.basic2.BestBank;
import com.kakaopay.finance.model.basic3.BankStatistics;
import com.kakaopay.finance.util.ConverterUtil;
import com.kakaopay.finance.util.FileReaderUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FinanceService {

    public SupplyListMain getFinanceNecessary1(){


//        "C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv")));
        List<String> fileReader =
        FileReaderUtil.getCsvfileInfo("C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv");
        fileReader = ConverterUtil.insertCsv(fileReader);
        System.out.println(">>>> " + fileReader.toString());

        return new SupplyListMain(1,"주택금융 공급현황",
                new ArrayList<SupplyList>(){{
                    add(new SupplyList(
                            1, "2004 년", 14145,
                            new ArrayList<SupplyBank>(){{
                                add(new SupplyBank("주택도시기금", 2143));
                                add(new SupplyBank("국민은행", 4356));
                                add(new SupplyBank("우리은행", 5342));
                                add(new SupplyBank("기타은행", 1324));
                            }}
                    ));

                    add(new SupplyList(
                            2, "2005 년", 23145,
                            new ArrayList<SupplyBank>(){{
                                add(new SupplyBank("주택도시기금", 1243));
                                add(new SupplyBank("국민은행", 5336));
                                add(new SupplyBank("우리은행", 4849));
                                add(new SupplyBank("기타은행", 1093));
                            }}
                    ));

                    add(new SupplyList(
                            3, "2017 ", 33145,
                            new ArrayList<SupplyBank>(){{
                                add(new SupplyBank("주택도시기금", 1243));
                                add(new SupplyBank("국민은행", 5336));
                                add(new SupplyBank("우리은행", 4849));
                                add(new SupplyBank("기타은행", 1093));
                            }}
                    ));

                }}
        );
    }

    public BestBank getFinanceNecessary2(){
        return new BestBank(2010, "국민은행");
    }

    public BankStatistics getFinanceNecessary3(){

        return new BankStatistics("외환은행"
//                new ArrayList<YearName>(){{
//                    add(new YearName(2008, 78));
//                    add(new YearName(2015, 1702));
//                }}
        );

    }

}