package com.kakaopay.finance.controller;

import com.kakaopay.finance.model.basic1.SupplyListTotal;
import com.kakaopay.finance.model.basic2.BestBank;
import com.kakaopay.finance.model.basic3.BankStatistics;
import com.kakaopay.finance.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/f")
public class FinanceController {

    @Autowired
    FinanceService service;

    @GetMapping("/main")
    public String index(){
        return "/finance/grid.html";
    }

    /* 기본문제 (1) 데이터 파일에서 각 레코드를 데이터베이스에 저장하는 API 개발 */
    @GetMapping("/insert")
    public String insertDate(){
        System.out.println("@@@@ Controller : insertData");
        return service.insertData();
    }

    /* 기본문제_(2) : 주택금융 공급 금융기관(은행) 목록을 출력하는 API를 개발하세요.  */
    @GetMapping("/bankList")
    @ResponseBody
    public Object getBankList(){
//        return service.getBankList();
        return service.getBankList();
    }

    /* 기본문제_(3) : 년도별 각 금융기관의 지원금액 합계 */
    @GetMapping("/api1")
    @ResponseBody
    public SupplyListTotal getBankAmountPerYear(){

        return service.getBankAmountPerYear();
    }

    /* 기본문제_(4) : 각 년도별 각 기관의 전체 지원금액 중에서 가장 큰 금액의 기관명  */
    @GetMapping("/api2")
    @ResponseBody
    public BestBank getFinanceNecessary2(){

        return service.getFinanceNecessary2();
    }

    /* 기본문제_(5) : 전체 년도(2005~2016)에서 외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액  */
    @GetMapping("/api3")
    @ResponseBody //json형식으로 보낼때 필요함~.~
    public BankStatistics getFinanceNecessary3(){

        return service.getFinanceNecessary3();
    }

    /* 추가문제 : 특정 은행의 특정 달에 대해서 2018년도 해당 달에 금융지원 금액을 예측 */

}