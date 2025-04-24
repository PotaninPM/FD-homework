package org.potaninpm.job

import org.potaninpm.model.CurrencyDto
import org.potaninpm.service.CbrApiService
import org.potaninpm.service.CurrencyService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.math.abs

@Component
class CurrencyMonitoringJob(
    private val cbrApiService: CbrApiService,
    private val currencyService: CurrencyService
) {
    private val logger = LoggerFactory.getLogger(CurrencyMonitoringJob::class.java)
    
    @Scheduled(fixedRate = 3600000) // Run every hour (3600000 ms)
    fun monitorCurrencyRates() {
        logger.info("Starting currency monitoring job")
        
        val cbrResponse = cbrApiService.fetchCurrencyRates()
        if (cbrResponse == null) {
            logger.error("Failed to fetch currency rates, skipping monitoring")
            return
        }
        
        val currencies = currencyService.getAllCurrencies()
        
        currencies.forEach { currency ->
            val charCode = currency.baseCurrency
            val cbrCurrency = cbrResponse.Valute[charCode]
            
            if (cbrCurrency != null) {
                checkPriceChange(currency.name, charCode, currency.priceChangeRange, cbrCurrency, currency.description)
            }
        }
    }
    
    private fun checkPriceChange(name: String, charCode: String, priceChangeRange: String, cbrCurrency: CurrencyDto, description: String?) {
        val priceChange = calculatePercentageChange(cbrCurrency.Value, cbrCurrency.Previous)
        val rangeValue = parseRangeValue(priceChangeRange)
        
        // If rangeValue is negative, we're looking for a decrease
        val isInRange = if (rangeValue < 0) {
            priceChange <= rangeValue
        } else {
            priceChange >= rangeValue
        }
        
        if (isInRange) {
            val notification = description ?: "$name changed by $priceChange% (threshold: $priceChangeRange)"
            logger.info("CURRENCY NOTIFICATION: $notification")
            // Print to console directly in addition to logging
            println("CURRENCY NOTIFICATION: $notification")
        }
    }
    
    private fun calculatePercentageChange(current: Double, previous: Double): Double {
        return ((current - previous) / previous) * 100
    }
    
    private fun parseRangeValue(priceChangeRange: String): Double {
        return try {
            priceChangeRange.replace("%", "").trim().toDouble()
        } catch (e: Exception) {
            logger.error("Failed to parse price change range: $priceChangeRange", e)
            0.0
        }
    }
} 