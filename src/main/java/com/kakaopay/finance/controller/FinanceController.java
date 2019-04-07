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

    @GetMapping("/api1")
    @ResponseBody
    public SupplyListTotal getFinanceNecessary1(){

        return service.getFinanceNecessary1();
    }


    @GetMapping("/api2")
    @ResponseBody
    public BestBank getFinanceNecessary2(){

        return service.getFinanceNecessary2();
    }


    @GetMapping("/api3")
    @ResponseBody //json형식으로 보낼때 필요함~.~
    public BankStatistics getFinanceNecessary3(){

        return service.getFinanceNecessary3();
    }

    @GetMapping("/insert")
    public void insertDate(){
        service.insertData();
    }

}