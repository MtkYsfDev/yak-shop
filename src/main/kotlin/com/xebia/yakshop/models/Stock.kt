package com.xebia.yakshop.models

data class Stock(val milk: Double, val skins: Int) {
    override fun toString(): String =
        """
            In Stock :
                ${this.milk}  liters of milk
                ${this.skins}  skins of wool
        """.trimIndent()
}
