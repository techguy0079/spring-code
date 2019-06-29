package com.training.spring.service;

public interface EmailService {
	
	public void sendMail(String toAddress,String fromAddress,String  content);

}
