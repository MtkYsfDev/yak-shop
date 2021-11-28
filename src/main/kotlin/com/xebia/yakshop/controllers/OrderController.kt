package com.xebia.yakshop.controllers

import com.xebia.yakshop.models.Order
import com.xebia.yakshop.models.Stock
import com.xebia.yakshop.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class OrderController(private val orderService: OrderService) {
    @PostMapping("/order/{days}", consumes = ["application/json", "application/xml"])
    fun queryOrder(@PathVariable days: Int, @RequestBody order: Order): ResponseEntity<Any> {
        //TODO: Handle empty herd

        val stock: Stock = orderService.queryOrder(days, order)
        val result: HashMap<String, Any> = HashMap()
        if (order.order.milk <= stock.milk) {
            result["milk"] = order.order.milk
        }
        if (order.order.skins <= stock.skins) {
            result["skins"] = order.order.skins
        }
        val stockMilk = result.containsKey("milk")
        val stockSkins = result.containsKey("skins")
        return if (stockMilk && stockSkins) {
            ResponseEntity.status(HttpStatus.CREATED).body(result)
        } else if (stockMilk || stockSkins) {
            ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(result)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry ${order.customer}, we could not fulfil your order")
        }

    }
}
