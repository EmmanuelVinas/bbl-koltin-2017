package io.monkeypatch.bbl.kotlin

import kotlin.test.Test
import kotlin.test.assertEquals


class ProductTest {

    @Test
    fun `Total price should be 0 when no product`() {
        assertEquals(0.0, Product("product", 10.0, quantity = 0).totalPrice())

    }

    @Test
    fun `Total price should be quantity * unit price when a quantity is defined`() {
        assertEquals(30.0, Product("product", 10.0, 3).totalPrice())
    }

}