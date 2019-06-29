package com.training.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.training.spring.repository.AccountRepository;
import com.training.spring.repository.JdbcAccountRepositoryImpl;
import com.training.spring.repository.JdbcRewardRepositoryImpl;
import com.training.spring.repository.JdbcTransactionRepositoryImpl;
import com.training.spring.repository.RewardRepository;
import com.training.spring.repository.TransactionRepository;
import com.training.spring.service.BankService;
import com.training.spring.service.BankServiceImpl;
import com.training.spring.service.EmailService;
import com.training.spring.service.EmailServiceImpl;

@Configuration
@Import({InfraConfiguration.class})
public class AppConfigurationJava {

	@Autowired
	private DataSource dataSource;
	/*
	 * method annotated with @Bean is equivalent to <bean/> tag in xml. method name
	 * will the bean
	 * 
	 * id by default . You can configure a bean name using the following syntax :
	 * 
	 * @Bean(name="ds")
	 */

	/*
	@Bean
	public DataSource dataSource() {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/springdb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;

	}*/

	@Bean
	public AccountRepository accountRepository() {
		return new JdbcAccountRepositoryImpl(dataSource);
	}

	@Bean
	public RewardRepository rewardRepository() {
		return new JdbcRewardRepositoryImpl(dataSource);
	}

	@Bean
	public TransactionRepository transactionRepository() {
		return new JdbcTransactionRepositoryImpl(dataSource);
	}

	@Bean
	public EmailService emailService() {
		return new EmailServiceImpl();
	}

	@Bean
	public BankService bankService() {
		BankServiceImpl bankServiceImpl = new BankServiceImpl();
		bankServiceImpl.setAccountRepository(accountRepository());
		bankServiceImpl.setEmailService(emailService());
		bankServiceImpl.setRewardRepository(rewardRepository());
		bankServiceImpl.setTransactionRepository(transactionRepository());
		return bankServiceImpl;
	}
}
