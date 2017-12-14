package io.monkeypatch.bbl.kotlin

import kotlinx.html.HtmlBlockTag
import kotlinx.html.div
import kotlinx.html.i

fun HtmlBlockTag.renderProduct(product: Product) {
    println("Rendering product : ${product.title}")
    div(classes = "product") {
        +"- ${product.title}"
        i {
            +" : ${product.price}€ * ${(product.quantity ?: 0)}  = ${product.totalPrice()}€"
        }
    }
}