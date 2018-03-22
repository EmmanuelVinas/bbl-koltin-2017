package io.monkeypatch.bbl.kotlin

import kotlin.test.Test
import kotlin.test.assertEquals


class SaleTest {

    @Test
    fun `verify total sale price`() {
        val sale = Sale(listOf(
            Product("Product 1", 10.0),
            Product("Product 1", 2.0, quantity = 2)
        ))

        assertEquals(14.0, sale.totalAmount())
    }
}
