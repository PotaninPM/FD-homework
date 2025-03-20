package org.potaninpm.model

data class Currency(
    val id: String,
    val name: String,
    val baseCurrency: String = "RUB",
    val priceChangeRange: String,
    val description: String? = null
)

data class CurrencyRequest(
    val name: String,
    val baseCurrency: String = "RUB",
    val priceChangeRange: String,
    val description: String? = null
) 