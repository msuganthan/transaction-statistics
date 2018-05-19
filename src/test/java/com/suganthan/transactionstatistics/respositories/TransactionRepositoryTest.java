package com.suganthan.transactionstatistics.respositories;

import com.suganthan.transactionstatistics.exceptions.ExpiredTransactionException;
import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.repositories.TransactionRepository;
import com.suganthan.transactionstatistics.repositories.impl.TransactionRepositoryImpl;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

/**
 * Created by msuganthan on 19/5/18.
 */
public class TransactionRepositoryTest {

    TransactionRepository transactionRepository = new TransactionRepositoryImpl();

    @Test
    public void shouldAbleToAddTransactionsWithSixtySecondRange() throws InterruptedException{
        //Given
        Transaction transactionOne = new Transaction(12.3, Instant.now().toEpochMilli());
        Thread.sleep(1000);
        Transaction transactionTwo = new Transaction(12.3, Instant.now().toEpochMilli());

        //when
        transactionRepository.addTransaction(transactionOne);
        transactionRepository.addTransaction(transactionTwo);

        //Then
        Assert.assertEquals(2, transactionRepository.getAllTransactions().size());
    }

    @Test(expected = ExpiredTransactionException.class)
    public void shouldNotAbleToAddTransactionsWithExpiredTimeRange() {
        //Given
        Transaction transactionOne = new Transaction(12.3, Instant.now().minusSeconds(60).toEpochMilli());

        //when
        transactionRepository.addTransaction(transactionOne);
    }

}
