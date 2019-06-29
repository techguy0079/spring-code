package com.training.spring.repository;

import java.sql.SQLException;
import java.util.List;

import com.training.spring.model.TransactionDetail;



public interface TransactionRepository {

	public Long addTransaction(TransactionDetail transactionDetail) throws SQLException;
	public List<TransactionDetail> getAllTransactionDetailsByAccountNumber(Long accountNumber) throws SQLException;
}
