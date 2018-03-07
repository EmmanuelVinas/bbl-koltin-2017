package io.monkeypatch.bbl.kotlin

import kotlinx.serialization.SerialContext
import kotlinx.serialization.internal.DoubleSerializer
import kotlinx.serialization.json.JSON

fun Product.toJSON():String =
    JSON(context = createSerialContext()).stringify(this)

fun Product.Companion.fromJSON(json : String): Product
        = JSON(context = createSerialContext()).parse(json)

fun createSerialContext()= SerialContext().apply {
    registerSerializer(Double::class, DoubleSerializer)
}
