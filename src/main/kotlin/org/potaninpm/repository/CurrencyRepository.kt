package org.potaninpm.repository

import org.potaninpm.model.Currency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CurrencyRepository : JpaRepository<Currency, String> 