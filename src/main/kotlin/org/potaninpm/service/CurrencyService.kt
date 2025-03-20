package org.potaninpm.service

import org.potaninpm.model.Currency
import org.potaninpm.model.CurrencyRequest
import org.potaninpm.repository.CurrencyRepository
import org.springframework.stereotype.Service

@Service
class CurrencyService(private val currencyRepository: CurrencyRepository) {

    fun getAllCurrencies(): List<Currency> = currencyRepository.findAll()

    fun getCurrencyById(id: String): Currency? = currencyRepository.findById(id)

    fun createCurrency(request: CurrencyRequest): Currency {
        val currency = Currency(
            id = "",
            name = request.name,
            baseCurrency = request.baseCurrency,
            priceChangeRange = request.priceChangeRange,
            description = request.description
        )
        return currencyRepository.create(currency)
    }

    fun updateCurrency(id: String, request: CurrencyRequest): Currency? {
        val existing = currencyRepository.findById(id) ?: return null
        val updated = existing.copy(
            name = request.name,
            baseCurrency = request.baseCurrency,
            priceChangeRange = request.priceChangeRange,
            description = request.description
        )
        return currencyRepository.save(updated)
    }

    fun deleteCurrency(id: String): Boolean {
        if (!currencyRepository.exists(id)) {
            return false
        }
        currencyRepository.delete(id)
        return true
    }
} 