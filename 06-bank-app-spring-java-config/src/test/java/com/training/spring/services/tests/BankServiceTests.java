package com.training.spring.services.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.training.spring.config.AppConfigurationJava;
import com.training.spring.service.BankService;



public class BankServiceTests {	
	
	private BankService bankService;
	
	
	@Before
	public void init() throws Exception{
		
		
		AnnotationConfigApplicationContext context= 
				new AnnotationConfigApplicationContext(AppConfigurationJava.class);
		
		//AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(AppConfigurationJava.class,InfraConfiguration.class);	
		bankService=context.getBean("bankService",BankService.class);
		
		
		//Datasource is closed , issue comes if i dont comment 
		//context.close();

		
		
	}	

	@Test
	public void testTransfer() throws SQLException{
		
		
		Long transactionId=bankService.transfer(new Long(1), new Long(2), 5000);
		assertNotNull(transactionId);
	}
	

}
