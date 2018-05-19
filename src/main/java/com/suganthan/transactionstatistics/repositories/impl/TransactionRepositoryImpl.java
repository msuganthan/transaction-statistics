package com.suganthan.transactionstatistics.repositories.impl;

import com.suganthan.transactionstatistics.exceptions.ExpiredTransactionException;
import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.repositories.TransactionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.suganthan.transactionstatistics.utils.TransactionsUtils.MAX_ALLOWED_SECONDS;
import static com.suganthan.transactionstatistics.utils.TransactionsUtils.isValidTransaction;

/**
 * Repo for transactions and statistics.
 * Created by msuganthan on 19/5/18.
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private ConcurrentNavigableMap<Long, Transaction> transactionsMap = new ConcurrentSkipListMap<>();

    /**
     * Adds transaction to transactionsMap if the transaction made in valid time.
     * Else throw ExpiredTransactionException
     * @param transaction
     */
    @Override
    public void addTransaction(Transaction transaction) {
        Long transactionTimeStamp = transaction.getTimestamp();
        if(!isValidTransaction(transactionTimeStamp)) {
            throw new ExpiredTransactionException("Invalid transaction time");
        }
        long toSeconds = Instant.ofEpochMilli(transactionTimeStamp).getEpochSecond();
        //ToDo: There is possibility multiple transactions can happen in a seconds.
        transactionsMap.put(toSeconds, transaction);
    }

    /**
     * Gets all the transaction for last 60 seconds.
     * @return list of {@link Transaction}
     */
    @Override
    public List<Transaction> getAllTransactions() {
        return getTransactionsFromLastSixtySeconds()
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Removes older transactions for every fix seconds.
     */
    @Scheduled(fixedDelay = 5 * 1000)
    public void removeOlderTransaction() {
        final ConcurrentNavigableMap<Long, Transaction> olderTransaction = getTransactionOlderThanSixtySeconds();
        if(olderTransaction.size() > 0) {
            olderTransaction.clear();
        }
    }

    private ConcurrentNavigableMap<Long, Transaction> getTransactionsFromLastSixtySeconds() {
        return transactionsMap.tailMap(Instant.now().minusSeconds(MAX_ALLOWED_SECONDS).getEpochSecond());
    }

    private ConcurrentNavigableMap<Long, Transaction> getTransactionOlderThanSixtySeconds() {
        return transactionsMap.headMap(Instant.now().minusSeconds(MAX_ALLOWED_SECONDS).getEpochSecond());
    }
}
