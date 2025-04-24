package org.potaninpm.controller

import org.potaninpm.job.CurrencyMonitoringJob
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/jobs")
class JobController(private val currencyMonitoringJob: CurrencyMonitoringJob) {
    
    @PostMapping("/currency-monitoring/trigger")
    fun triggerCurrencyMonitoring(): ResponseEntity<String> {
        currencyMonitoringJob.monitorCurrencyRates()
        return ResponseEntity.ok("Currency monitoring job triggered successfully")
    }
} 