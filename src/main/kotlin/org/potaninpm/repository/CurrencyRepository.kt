package org.potaninpm.repository

import org.potaninpm.model.Currency
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Repository
class CurrencyRepository {
    private val currencies = ConcurrentHashMap<String, Currency>()

    fun findAll(): List<Currency> = currencies.values.toList()

    fun findById(id: String): Currency? = currencies[id]

    fun save(currency: Currency): Currency {
        currencies[currency.id] = currency
        return currency
    }

    fun create(currency: Currency): Currency {
        val id = UUID.randomUUID().toString()
        val newCurrency = currency.copy(id = id)
        currencies[id] = newCurrency
        return newCurrency
    }

    fun delete(id: String) {
        currencies.remove(id)
    }

    fun exists(id: String): Boolean = currencies.containsKey(id)
} 