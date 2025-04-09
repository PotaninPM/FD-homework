package org.potaninpm.service

import org.potaninpm.model.Currency
import org.potaninpm.model.CurrencyRequest
import org.potaninpm.repository.CurrencyRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CurrencyService(private val currencyRepository: CurrencyRepository) {

    fun getAllCurrencies(): List<Currency> = currencyRepository.findAll()

    fun getCurrencyById(id: String): Currency? = currencyRepository.findById(id).orElse(null)

    fun createCurrency(request: CurrencyRequest): Currency {
        val currency = Currency(
            id = UUID.randomUUID().toString(),
            name = request.name,
            baseCurrency = request.baseCurrency,
            priceChangeRange = request.priceChangeRange,
            description = request.description
        )
        return currencyRepository.save(currency)
    }

    fun updateCurrency(id: String, request: CurrencyRequest): Currency? {
        if (!currencyRepository.existsById(id)) {
            return null
        }
        
        val updated = Currency(
            id = id,
            name = request.name,
            baseCurrency = request.baseCurrency,
            priceChangeRange = request.priceChangeRange,
            description = request.description
        )
        return currencyRepository.save(updated)
    }

    fun deleteCurrency(id: String): Boolean {
        if (!currencyRepository.existsById(id)) {
            return false
        }
        currencyRepository.deleteById(id)
        return true
    }
} 