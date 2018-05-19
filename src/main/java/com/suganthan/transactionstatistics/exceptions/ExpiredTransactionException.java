package com.suganthan.transactionstatistics.exceptions;

/**
 * Expired Transaction Exception.
 * Created by msuganthan on 19/5/18.
 */
public class ExpiredTransactionException extends RuntimeException {
    public ExpiredTransactionException(String errorMessage) {
        super(errorMessage);
    }
}
