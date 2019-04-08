package com.kakaopay.finance.service;

import com.kakaopay.finance.jpa.AgencyRepository;
import com.kakaopay.finance.jpa.FinanceRepository;
import com.kakaopay.finance.jpa.SupplyDataRepository;
import com.kakaopay.finance.model.basic1.SupplyBank;
import com.kakaopay.finance.model.basic1.SupplyList;
import com.kakaopay.finance.model.basic1.SupplyListTotal;
import com.kakaopay.finance.model.basic2.BestBank;
import com.kakaopay.finance.model.basic3.BankStatistics;
import com.kakaopay.finance.model.basic3.YearAmount;
import com.kakaopay.finance.model.file.Agency;
import com.kakaopay.finance.model.file.FileDto;
import com.kakaopay.finance.model.file.SupplyData;
import com.kakaopay.finance.util.ConverterUtil;
import com.kakaopay.finance.util.FileReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FinanceService {

    @Autowired
    FinanceRepository financeRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    SupplyDataRepository supplyDataRepository;

    enum Bank{
        주택도시기금1, 국민은행, 우리은행, 신한은행, 한국시티은행
        ,하나은행, 농협은행_수협은행, 외환은행, 기타은행
    }

    /* 기본문제 (1) 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발 */
    public String insertData(){

        List<String> fileReader = FileReaderUtil.getCsvfileInfo("C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv");
        fileReader = ConverterUtil.convertToBasicFormat(fileReader);

        int year=0;
        int month=0;
        List<String> headRow = new ArrayList<>();
        for(int i=0; i<fileReader.size(); i++){

            if(i==0) {
                headRow = Arrays.asList(fileReader.get(i).split(","));
                continue;
            }

            List<String> supplyList = Arrays.asList(fileReader.get(i).split(","));
            for(int j=0; j<supplyList.size(); j++){
                if(j==0){
                    year = Integer.parseInt(supplyList.get(j));
                    continue;
                }

                if(j==1){
                    month = Integer.parseInt(supplyList.get(j));
                    continue;
                }

                supplyDataRepository.save(
                        new SupplyData().builder()
                                .year(year).month(month)
                                .bank(headRow.get(j).substring(0,headRow.get(j).length()-4))
                                .amount(Integer.parseInt(supplyList.get(j)))
                                .build()
                );
            }
        }
//        List<String> fileReader = FileReaderUtil.getCsvfileInfo("C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv");
//        fileReader = ConverterUtil.convertToBasicFormat(fileReader);
//
//        for(int i=0; i<fileReader.size(); i++){
//
//            if(i==0){
//                List<String> agencyArr = Arrays.asList(fileReader.get(i).split(","));
//
//                for(int j=2; j< agencyArr.size(); j++){//년도(i=0), 월(i=1) 제외하고, Agency 테이블에 추가
//                    System.out.println("@@@ " + agencyArr.get(j));
//                    agencyRepository.save(
//                            new Agency().builder().name(agencyArr.get(j)).build()
//                    );
//                }
//
//            }else{
//                Map<String, String> map = ConverterUtil.convertFromStringToMapFormat(fileReader.get(i));
//
//                financeRepository.save(
//                        new FileDto()
//                                .builder()
//                                .year(Integer.parseInt(map.get("year")))
//                                .month(Integer.parseInt(map.get("month")))
//                                .molitFd(Integer.parseInt(map.get("molitFd")))
//                                .kbBank(Integer.parseInt(map.get("kbBank")))
//                                .wrBank(Integer.parseInt(map.get("wrBank")))
//                                .shBank(Integer.parseInt(map.get("shBank")))
//                                .citiBank(Integer.parseInt(map.get("citiBank")))
//                                .hnBank(Integer.parseInt(map.get("hnBank")))
//                                .nhBank(Integer.parseInt(map.get("nhBank")))
//                                .kebBank(Integer.parseInt(map.get("kebBank")))
//                                .etcBank(Integer.parseInt(map.get("etcBank")))
//                                .build()
//                );
//            }
//        }

        return "success";
    }

    public Page<FileDto> getFileDtoList(Pageable pageable) {
        return financeRepository.findAll(pageable);
    }

    /* 기본문제_(2) : 주택금융 공급 금융기관(은행) 목록을 출력하는 API를 개발하세요.  */
    public Page<Agency> getBankList(Pageable pageable){
        return agencyRepository.findAll(pageable);
    }

    /* 기본문제_(3) : 년도별 각 금융기관의 지원금액 합계 */
    public SupplyListTotal getFinanceNecessary1(){
//        "C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv")));
        List<String> fileReader =
        FileReaderUtil.getCsvfileInfo("C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv");
        fileReader = ConverterUtil.convertToBasicFormat(fileReader);
        System.out.println(">>>> " + fileReader.toString());

        return new SupplyListTotal(1,"주택금융 공급현황",
                new ArrayList<SupplyList>(){{
                    add(new SupplyList(
                            "2004 년", 14145,
                            new ArrayList<SupplyBank>(){{
                                add(new SupplyBank("주택도시기금", 2143));
                                add(new SupplyBank("국민은행", 4356));
                                add(new SupplyBank("우리은행", 5342));
                                add(new SupplyBank("기타은행", 1324));
                            }}
                    ));

                    add(new SupplyList(
                            "2005 년", 23145,
                            new ArrayList<SupplyBank>(){{
                                add(new SupplyBank("주택도시기금", 1243));
                                add(new SupplyBank("국민은행", 5336));
                                add(new SupplyBank("우리은행", 4849));
                                add(new SupplyBank("기타은행", 1093));
                            }}
                    ));

                    add(new SupplyList(
                            "2017 ", 33145,
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

    /* 기본문제_(4) : 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명  */
    public BestBank getFinanceNecessary2(){

        return new BestBank(2010, "국민은행");
    }

    /* 기본문제_(5) : 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액  */
    public BankStatistics getFinanceNecessary3(){

        return new BankStatistics("외환은행",
                new ArrayList<YearAmount>(){{
                    add(new YearAmount(2008, 78));
                    add(new YearAmount(2015, 1702));
                }}
        );
    }

    /* 추가문제 : 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측 */








    //    public String insertData(){
//
//        List<Map<String, Integer>> param = new ArrayList<>();
//        param.add(
//                convertFromStringToMapFormat("2011,7,5258,2296,1973,1213,2,573,1778,233,1062")
//        );
//
//        new FinanceOperation().begin().insertEntity(param).commitAndClose();
//        return "Success";
//    }
}