package com.tdd.loans;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

class RepaymentAmountTests {
	
	@Spy
	LoanApplication loanApplication;
	
	LoanCalculatorController controller;
	
	@BeforeEach
	public void setup() {
		LoanRepository repository = mock(LoanRepository.class);
		JavaMailSender mail = mock(JavaMailSender.class);
		RestTemplate rest = mock(RestTemplate.class);
		
		loanApplication = spy(new LoanApplication());
		
		controller = new LoanCalculatorController();
		controller.setData(repository);
		controller.setMailSender(mail);
		controller.setRestTemplate(rest);
	}

	@Test
	public void test1YearLoanWholePounds() {
	
		loanApplication.setPrincipal(1200);
		loanApplication.setTermInMonths(12);
		doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
		
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(110), loanApplication.getRepayment());
	}
	
	@Test
	public void test2YearLoanWholePounds() {
	
		loanApplication.setPrincipal(1200);
		loanApplication.setTermInMonths(24);

		doReturn(new BigDecimal(10)).when(loanApplication).getInterestRate();
		
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(60), loanApplication.getRepayment());
	}
	
	@Test
	public void test5YearLoanWholePounds() {
	
		loanApplication.setPrincipal(5000);
		loanApplication.setTermInMonths(60);

		doReturn(new BigDecimal(6.5)).when(loanApplication).getInterestRate();
		
		controller.processNewLoanApplication(loanApplication);
		
		assertEquals(new BigDecimal(111), loanApplication.getRepayment());
	}
}
