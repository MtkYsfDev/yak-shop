package com.xebia.yakshop.service.impl

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.models.Stock
import com.xebia.yakshop.repositories.HerdRepository
import com.xebia.yakshop.service.StockService
import com.xebia.yakshop.utils.Functions
import org.springframework.stereotype.Service

@Service
class StockServiceImpl(private val herdRepository: HerdRepository) : StockService {
    override fun getStock(days: Int): Stock {
        val herd: Herd = herdRepository.findAll().iterator().next()
        return Functions.calculateStock(herd, days).first
    }

}
