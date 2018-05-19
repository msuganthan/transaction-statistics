package com.suganthan.transactionstatistics.services.impl;

import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.repositories.TransactionRepository;
import com.suganthan.transactionstatistics.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Transactions service.
 * Created by msuganthan on 19/5/18.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }
}
