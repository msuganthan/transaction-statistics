package com.suganthan.transactionstatistics.controllers;

import com.suganthan.transactionstatistics.models.Statistics;
import com.suganthan.transactionstatistics.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Statistics controller.
 * Created by msuganthan on 19/5/18.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Returns transaction statistics
     * @return
     */
    @GetMapping
    public Statistics getStatistics() {
        return statisticsService.getStatistics();
    }
}
