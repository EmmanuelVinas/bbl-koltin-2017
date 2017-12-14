package io.monkeypatch.bbl.kotlin

import org.amshove.kluent.`should be`
import org.junit.Test

class ProductTest {

    @Test
    fun `Total price should be 0 when no product`() {
        Product("product", 10.0, null).totalPrice() `should be` 0.0
    }

}