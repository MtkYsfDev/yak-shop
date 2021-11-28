package com.xebia.yakshop.service.impl

import com.xebia.yakshop.models.Herd
import com.xebia.yakshop.models.Order
import com.xebia.yakshop.models.Stock
import com.xebia.yakshop.repositories.HerdRepository
import com.xebia.yakshop.service.OrderService
import com.xebia.yakshop.utils.Functions
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val herdRepository: HerdRepository) : OrderService {
    override fun queryOrder(days: Int, order: Order): Stock {
        val herd: Herd = herdRepository.findAll().iterator().next()
        return Functions.calculateStock(herd, days).first
    }

}
