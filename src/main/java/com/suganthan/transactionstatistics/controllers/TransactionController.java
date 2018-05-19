package com.suganthan.transactionstatistics.controllers;

import com.suganthan.transactionstatistics.exceptions.ExpiredTransactionException;
import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Transaction Controller.
 * Created by msuganthan on 19/5/18.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Add transaction.
     * @param transaction
     * @return
     */
    @PostMapping
    public ResponseEntity addTransaction(@RequestBody Transaction transaction) {
        try {
            transactionService.addTransaction(transaction);
        } catch (ExpiredTransactionException expection) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Transaction added successfully");

    }

}
