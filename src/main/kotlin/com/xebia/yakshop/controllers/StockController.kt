package com.xebia.yakshop.controllers

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.models.Stock
import com.xebia.yakshop.repositories.HerdRepository
import com.xebia.yakshop.utils.Functions
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@Controller
class StockController(private val herdRepository: HerdRepository) {

    @GetMapping("/stock/{days}")
    fun getStock(@PathVariable days: Int): ResponseEntity<Stock> {
        val herd: Herd = herdRepository.findAll().iterator().next()
        val stock: Stock = Functions.calculateStock(herd, days).first
        return ResponseEntity.ok(stock)
    }
}
