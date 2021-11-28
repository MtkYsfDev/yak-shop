package com.xebia.yakshop.service

import com.xebia.yakshop.models.Order
import com.xebia.yakshop.models.Stock

interface OrderService {

    fun queryOrder(days: Int, order: Order): Stock

}


