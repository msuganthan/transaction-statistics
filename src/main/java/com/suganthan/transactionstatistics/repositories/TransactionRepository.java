package com.suganthan.transactionstatistics.repositories;

import com.suganthan.transactionstatistics.models.Transaction;

import java.util.List;

/**
 * Created by msuganthan on 19/5/18.
 */
public interface TransactionRepository {

    void addTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
}
