package org.potaninpm.controller

import org.potaninpm.model.Currency
import org.potaninpm.model.CurrencyRequest
import org.potaninpm.service.CurrencyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/currencies")
class CurrencyController(private val currencyService: CurrencyService) {

    @GetMapping
    fun getCurrencies(): ResponseEntity<List<Currency>> {
        val currencies = currencyService.getAllCurrencies()
        return ResponseEntity.ok(currencies)
    }

    @PostMapping
    fun addCurrency(@RequestBody request: CurrencyRequest): ResponseEntity<Unit> {
        currencyService.createCurrency(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{id}")
    fun getCurrency(@PathVariable id: String): ResponseEntity<Currency> {
        val currency = currencyService.getCurrencyById(id)
            ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(currency)
    }

    @PutMapping("/{id}")
    fun updateCurrency(
        @PathVariable id: String, 
        @RequestBody request: CurrencyRequest
    ): ResponseEntity<Unit> {
        val updatedCurrency = currencyService.updateCurrency(id, request) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteCurrency(@PathVariable id: String): ResponseEntity<Unit> {
        val deleted = currencyService.deleteCurrency(id)

        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
} 