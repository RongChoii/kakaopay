package com.kakaopay.finance.service;

import com.kakaopay.finance.jpa.BestBankRepository;
import com.kakaopay.finance.jpa.SupplyBankRepository;
import com.kakaopay.finance.jpa.SupplyDataRepository;
import com.kakaopay.finance.jpa.YearAmountRepository;
import com.kakaopay.finance.model.basic1.SupplyBank;
import com.kakaopay.finance.model.basic1.SupplyList;
import com.kakaopay.finance.model.basic1.SupplyListTotal;
import com.kakaopay.finance.model.basic2.BestBank;
import com.kakaopay.finance.model.basic3.BankStatistics;
import com.kakaopay.finance.model.basic3.YearAmount;
import com.kakaopay.finance.model.file.SupplyData;
import com.kakaopay.finance.util.ConverterUtil;
import com.kakaopay.finance.util.FileReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class FinanceService {

    @Autowired
    SupplyDataRepository supplyDataRepository;

    @Autowired
    SupplyBankRepository supplyBankRepository;

    @Autowired
    YearAmountRepository yearAmountRepository;

    @Autowired
    BestBankRepository bestBankRepository;

//    enum Bank{
//        주택도시기금1, 국민은행, 우리은행, 신한은행, 한국시티은행
//        ,하나은행, 농협은행_수협은행, 외환은행, 기타은행
//    }

    /* 기본문제 (1) 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발 */
    public String insertData(){

        List<String> fileReader = FileReaderUtil.getCsvfileInfo("C:/Users/rong/Desktop/카카오페이/2019경력공채_개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv");
        fileReader = ConverterUtil.convertToBasicFormat(fileReader);

        supplyDataRepository.deleteAll();

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

//    public Page<FileDto> getFileDtoList(Pageable pageable) {
//        return financeRepository.findAll(pageable);
//    }

    /* 기본문제_(2) : 주택금융 공급 금융기관(은행) 목록을 출력하는 API를 개발하세요.  --> 완료 */
    public Object getBankList(){
        return supplyDataRepository.findBankList();
    }

    /* 기본문제_(3) : 년도별 각 금융기관의 지원금액 합계 --> 완료 */
    public SupplyListTotal getBankAmountPerYear(){

        List<YearAmount> yearList = yearAmountRepository.selectYearTotal();
        System.out.println("@@@@@@ yearList : " + yearList.toString());

        return new SupplyListTotal(1, "주택금융 공급현황",
                new ArrayList<SupplyList>(){{
                    yearList.forEach(year -> {
                        add(new SupplyList(
                                String.valueOf(year.getYear())+"년",
                                year.getAmount(),
                                new HashMap<String, Object>(){{
                                    int i=0;
                                    for(SupplyBank sb : supplyBankRepository.selectAmountPerBankByYear(year.getYear())){
                                        put(supplyBankRepository.selectAmountPerBankByYear(year.getYear()).get(i).getBank()
                                        , supplyBankRepository.selectAmountPerBankByYear(year.getYear()).get(i).getSum_amount());
                                        i++;
                                    }
                                }}
//                                supplyBankRepository.selectAmountPerBankByYear(year.getYear())
//                                detailAmount.put(EBank.valueOf(supplyBankRepository.selectAmountPerBankByYear(year.getYear()).get(0).getBank()).getBankName(), 1234141414)
                        ));
                    });
                }}
        );

    }

    /* 기본문제_(4) : 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명  ?????????????????????? */
    public BestBank getFinanceNecessary2(){
        System.out.println("@@@@ 1");
        BestBank bestBank = null;
        int max = 0;
        for(int year : bestBankRepository.selectYearList()){
            System.out.println("@@@@ 2 " + year);
            for(BestBank b : bestBankRepository.selectAmountByYear(year)) {
                System.out.println("@@@@ 3 " + b.getYear() + "/" + bestBank.getBank() + "/" + b.getAmount());
                if(max < b.getAmount()) {
                    max = b.getAmount();
                    System.out.println("@@@@ 4 " + b.getBank() + "/" + max);
                    bestBank = new BestBank(year, b.getBank(), max);
                }
            }
        }
        System.out.println("@@@@ 5" + bestBank.getBank());
        System.out.println("@@@@ 5" + bestBank.getYear());

        return bestBank;
    }

    /* 기본문제_(5) : 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액  --> 완료 */
    public BankStatistics getFinanceNecessary3() {
        int min = 0, max = 0;
        List<YearAmount> yearAmountList = yearAmountRepository.selectYearAmount();
        HashMap<String, Integer> maxMin = new HashMap<>();

        for (int i = 0; i < yearAmountList.size(); i++) {
            if (i == 0) {
                maxMin.put("min", yearAmountList.get(i).getAmount());
                maxMin.put("max", yearAmountList.get(i).getAmount());
                maxMin.put("minYear", yearAmountList.get(i).getYear());
                maxMin.put("maxYear", yearAmountList.get(i).getYear());
            }
            if (maxMin.get("max") < yearAmountList.get(i).getAmount()) {
                maxMin.put("max", yearAmountList.get(i).getAmount());
                maxMin.put("maxYear", yearAmountList.get(i).getYear());
            }
            if (maxMin.get("min") > yearAmountList.get(i).getAmount()) {
                maxMin.put("min", yearAmountList.get(i).getAmount());
                maxMin.put("minYear", yearAmountList.get(i).getYear());
            }
        }

        return new BankStatistics("외환은행",
                new ArrayList<YearAmount>() {{
                    add(new YearAmount(maxMin.get("minYear"), maxMin.get("min")));
                    add(new YearAmount(maxMin.get("maxYear"), maxMin.get("max")));
                }}
        );
    }

    /* 추가문제 : 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측 */

}