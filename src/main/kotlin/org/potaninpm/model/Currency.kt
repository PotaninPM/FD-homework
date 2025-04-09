package org.potaninpm.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Column
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "currencies")
data class Currency(
    @Id
    val id: String = UUID.randomUUID().toString(),
    
    @Column(nullable = false)
    val name: String,
    
    @Column(nullable = false)
    val baseCurrency: String = "RUB",
    
    @Column(nullable = false)
    val priceChangeRange: String,
    
    @Column
    val description: String? = null
)

data class CurrencyRequest(
    val name: String,
    val baseCurrency: String = "RUB",
    val priceChangeRange: String,
    val description: String? = null
) 