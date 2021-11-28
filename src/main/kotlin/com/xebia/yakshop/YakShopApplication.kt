package com.xebia.yakshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan
@SpringBootApplication
class YakShopApplication

fun main(args: Array<String>) {
    runApplication<YakShopApplication>(*args)
}
