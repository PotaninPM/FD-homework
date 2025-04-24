package org.potaninpm.service

import org.potaninpm.model.CbrResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.slf4j.LoggerFactory

@Service
class CbrApiService(private val restTemplate: RestTemplate) {
    
    private val logger = LoggerFactory.getLogger(CbrApiService::class.java)
    private val cbrApiUrl = "https://www.cbr-xml-daily.ru/daily_json.js"
    
    fun fetchCurrencyRates(): CbrResponse? {
        return try {
            logger.info("Fetching")
            restTemplate.getForObject(cbrApiUrl, CbrResponse::class.java)
        } catch (e: Exception) {
            logger.error("Failed", e)
            null
        }
    }
} 