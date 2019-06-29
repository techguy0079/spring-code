package com.training.spring.service.tests;


import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.training.spring.service.BankService;




public class BankServiceTests {
	
	
	private BankService bankService;
	BasicDataSource dataSource;
	
	@Before
	public void init() throws Exception{
		 
		/*  //Not needed any as application-config.xml has the details
		dataSource= new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://localhost/springdb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		
		AccountRepository accountRepository= new JdbcAccountRepositoryImpl(dataSource);		
		TransactionRepository transactionRepository=new JdbcTransactionRepositoryImpl(dataSource);
		RewardRepository rewardRepository= new JdbcRewardRepositoryImpl(dataSource);		
		EmailService emailService= new EmailServiceImpl();
		
		bankService= new BankServiceImpl();
		bankService.setAccountRepository(accountRepository);
		bankService.setTransactionRepository(transactionRepository);
		bankService.setRewardRepository(rewardRepository);
		bankService.setEmailService(emailService);
		*/
		
		//For Multiple Files
		//ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:application-config.xml","classpath:infra-config.xml");
		
		//For testing
		//ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:application-config.xml","classpath:infra-config-test.xml");
		
		//using import in application-config.xml
		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:application-config.xml");
		
		bankService = appContext.getBean(BankService.class);
		//bankService = appContext.getBean("bankService",BankService.class);
		//bankService = (BankService) appContext.getBean("bankService");
		
		//setUpDb();
		appContext.close();
	}
	
	public void destroy(){
		
	}

	@Test
	public void testTransfer() throws SQLException{
		
		Long transactionId=bankService.transfer(new Long(1), new Long(2), 5000);
		assertNotNull(transactionId);
	}
	
	/*public void setUpDb() throws Exception{
		URL url=this.getClass().getClassLoader().getResource("dbscripts.sql");
		System.out.println(url);
		InputStream is=this.getClass().getClassLoader().getResourceAsStream("dbscripts.sql");
		BufferedReader reader= new BufferedReader(new InputStreamReader(is));
		List<String> queries= new ArrayList<String>();
		String line=reader.readLine();
		String query="";
		while(line!=null){
			query+=line;
			if(query.endsWith(";")){
				queries.add(query);
				System.out.println(query);
				query="";
			}
			line=reader.readLine();

		}
		Connection connection=dataSource.getConnection();
		Statement statmement=connection.createStatement();
		for(String sql:queries){
			statmement.executeUpdate(sql);
		}
		
		Account firstAccount= new Account(new Long(1),"Shiva",true,10000,"sivaprasad.valluru@gmail.com","Bangalore","India");
		Account secondAccount= new Account(new Long(2),"Prasad",true,20000,"siva@gmail.com","Bangalore","India");
		accountRepository.save(firstAccount);
		accountRepository.save(secondAccount);
	}*/
}
