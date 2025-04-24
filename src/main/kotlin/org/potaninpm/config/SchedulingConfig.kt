package org.potaninpm.config

import org.potaninpm.job.CurrencyMonitoringJob
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.slf4j.LoggerFactory

@Configuration
@Profile("dev")
@EnableScheduling
class DevSchedulingConfig(private val currencyMonitoringJob: CurrencyMonitoringJob) {

    @Scheduled(fixedRate = 60000)
    fun runCurrencyMonitoringJobMoreFrequently() {
        currencyMonitoringJob.monitorCurrencyRates()
    }
} 