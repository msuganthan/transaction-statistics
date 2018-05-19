package com.suganthan.transactionstatistics.services.impl;

import com.suganthan.transactionstatistics.models.Statistics;
import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.repositories.TransactionRepository;
import com.suganthan.transactionstatistics.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Statistics service.
 * Created by msuganthan on 19/5/18.
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Get all the transaction for last 60 seconds and calculates
     * statistics using {@link DoubleSummaryStatistics}
     * @return
     */
    @Override
    public Statistics getStatistics() {
        List<Transaction> transactionList = transactionRepository.getAllTransactions();
        DoubleSummaryStatistics summaryStatistics = transactionList.stream()
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();
        return new Statistics(summaryStatistics.getSum(),
                summaryStatistics.getAverage(),
                summaryStatistics.getMax(),
                summaryStatistics.getMin(),
                summaryStatistics.getCount());

    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }
}
