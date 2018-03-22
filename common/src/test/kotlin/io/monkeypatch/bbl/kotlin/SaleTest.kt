package io.monkeypatch.bbl.kotlin

import kotlin.test.Test
import kotlin.test.assertEquals


class SaleTest {

    @Test
    fun `verify total sale price`() {
        val sale = Sale(listOf(
            Product("Product 1", 10.0),
            Product("Product 2", 2.0, quantity = 2)
        ))

        assertEquals(14.0, sale.totalAmount())
    }

    @Test
    fun `verify add product`() {
        var sale = Sale()
        assertEquals(0.0, sale.totalAmount())
        sale = sale.addProduct(Product("Product 1", 10.0))
        assertEquals(10.0, sale.totalAmount())
        sale = sale.addProduct(Product("Product 2", 2.0, quantity = 2))
        assertEquals(14.0, sale.totalAmount())
    }
}
