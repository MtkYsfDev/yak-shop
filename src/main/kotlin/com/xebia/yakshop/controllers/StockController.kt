package com.xebia.yakshop.controllers

import com.xebia.yakshop.models.Stock
import com.xebia.yakshop.service.StockService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@Controller
class StockController(private val stockService: StockService) {

    @GetMapping("/stock/{days}")
    fun getStock(@PathVariable days: Int): ResponseEntity<Stock> {
        val stock = stockService.getStock(days)
        return ResponseEntity.ok(stock)
    }
}
