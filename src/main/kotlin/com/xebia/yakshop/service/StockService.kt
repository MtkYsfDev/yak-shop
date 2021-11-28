package com.xebia.yakshop.service

import com.xebia.yakshop.models.Stock
import org.springframework.web.bind.annotation.PathVariable

interface StockService {
    fun getStock(@PathVariable days: Int): Stock
}
