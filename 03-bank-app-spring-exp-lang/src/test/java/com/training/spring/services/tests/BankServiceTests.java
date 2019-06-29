package com.training.spring.services.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.training.spring.model.NumberRand;
import com.training.spring.service.BankService;



public class BankServiceTests {	
	
	private BankService bankService;
	
	private NumberRand numberRandom;
	@Before
	public void init() throws Exception{
		
//		ClassPathXmlApplicationContext context= 
//				new ClassPathXmlApplicationContext("application-config.xml","test-infrastructure-config.xml");	
		
//		ClassPathXmlApplicationContext context= 
//				new ClassPathXmlApplicationContext("application-config.xml","prod-infrastructure-config.xml");	
//		bankService=context.getBean("bankService",BankService.class);
//		
//		
//		context.close();
		
		ClassPathXmlApplicationContext context= 
				new ClassPathXmlApplicationContext("application-config.xml","prod-infrastructure-config.xml");	
		numberRandom = context.getBean(NumberRand.class);
		
		
	}	

	@Test
	public void testTransfer() throws SQLException{
		
		ExpressionParser parser = new SpelExpressionParser();
		String exp = (String )parser.parseExpression("'Hello'").getValue();
		System.out.println(numberRandom.getNumber());
		assertEquals("Hello",exp);
		assertThat(numberRandom.getNumber());
		//Long transactionId=bankService.transfer(new Long(1), new Long(2), 5000);
		//assertNotNull(transactionId);
	}
	

}
