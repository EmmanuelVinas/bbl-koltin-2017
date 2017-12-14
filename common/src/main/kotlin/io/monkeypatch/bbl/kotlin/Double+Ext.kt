package io.monkeypatch.bbl.kotlin

fun Double?.asValue() = this ?: 0.0

operator fun Double?.times(other: Double?) =
    this.asValue() * other.asValue()
