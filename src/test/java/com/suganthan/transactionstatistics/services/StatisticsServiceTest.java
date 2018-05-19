package com.suganthan.transactionstatistics.services;

import com.suganthan.transactionstatistics.models.Statistics;
import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.repositories.TransactionRepository;
import com.suganthan.transactionstatistics.services.impl.StatisticsServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by msuganthan on 19/5/18.
 */
public class StatisticsServiceTest {

    StatisticsServiceImpl statisticsService = new StatisticsServiceImpl();

    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        statisticsService.setTransactionRepository(transactionRepository);
    }

    @Test
    public void shouldAbleToReturnStatisticsForAllTransactions() {
        //Given
        List<Transaction> transactions = new ArrayList<>();
        Transaction transactionOne = new Transaction(12.3, Instant.now().minusSeconds(2).toEpochMilli());
        Transaction transactionTwo = new Transaction(12.3, Instant.now().minusSeconds(1).toEpochMilli());
        transactions.add(transactionOne);
        transactions.add(transactionTwo);
        Mockito.when(transactionRepository.getAllTransactions()).thenReturn(transactions);

        //When
        Statistics statistics = statisticsService.getStatistics();

        //Then
        Assert.assertEquals(Double.valueOf(12.3), statistics.getAvg());
        Assert.assertEquals(Long.valueOf(2), statistics.getCount());
        Assert.assertEquals(Double.valueOf(12.3), statistics.getMax());
        Assert.assertEquals(Double.valueOf(12.3), statistics.getMin());
        Assert.assertEquals(Double.valueOf(24.6), statistics.getSum());
    }
}
