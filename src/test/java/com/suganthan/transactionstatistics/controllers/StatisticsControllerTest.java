package com.suganthan.transactionstatistics.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suganthan.transactionstatistics.models.Statistics;
import com.suganthan.transactionstatistics.services.StatisticsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by msuganthan on 19/5/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StatisticsController.class)
public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StatisticsService statisticsService;

    @Test
    public void shouldAbleToReturnStatisticsForAvailableTransaction() throws Exception{
        Statistics statistics = new Statistics(12.0, 12.0, 12.0, 12.0, 1L);
        Mockito.when(statisticsService.getStatistics()).thenReturn(statistics);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/statistics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Statistics resultantStatistics = objectMapper.readValue(result.getResponse().getContentAsString(), Statistics.class);
        Assert.assertNotNull(resultantStatistics);

    }

}
