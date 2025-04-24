package org.potaninpm.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CbrResponse(
    val Date: String,
    val PreviousDate: String,
    val Valute: Map<String, CurrencyDto>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CurrencyDto(
    val ID: String,
    val NumCode: String,
    val CharCode: String,
    val Nominal: Int,
    val Name: String,
    val Value: Double,
    val Previous: Double
) 