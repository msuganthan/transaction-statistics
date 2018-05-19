package com.suganthan.transactionstatistics.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suganthan.transactionstatistics.exceptions.ExpiredTransactionException;
import com.suganthan.transactionstatistics.models.Transaction;
import com.suganthan.transactionstatistics.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

/**
 * Created by msuganthan on 19/5/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void shouldAbleToReturnCreatedWhenTransactionCreated() throws Exception {
        Transaction transaction = new Transaction(12.3, Instant.now().toEpochMilli());
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldAbleToReturnNoContentWhenTransactionNotCreated() throws Exception {
        Transaction transaction = new Transaction(12.3, Instant.now()
                .minusSeconds(5).toEpochMilli());

        Mockito.doThrow(new ExpiredTransactionException("Invalid transaction")).when(transactionService).addTransaction(ArgumentMatchers.any());
        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
