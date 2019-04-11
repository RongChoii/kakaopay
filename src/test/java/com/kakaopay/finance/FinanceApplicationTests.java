package com.kakaopay.finance;

import com.kakaopay.finance.service.FinanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FinanceApplicationTests {

	@Autowired
	ApplicationContext context;

	@Autowired
	FinanceService financeService;


	@Test
	public void contextLoads() {
		try {
			System.out.println(context);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void api1Test(){
		financeService.insertData();
	}

	@Test
	public void api2Test(){
		financeService.getBankList();
	}

	@Test
	public void api3Test(){
		financeService.getSumPerBankPerYear();
	}

	@Test
	public void api4Test(){
		financeService.getMaxPerData();
	}

	@Test
	public void api5Test(){
		financeService.getMaxMinForKeb();
	}


}