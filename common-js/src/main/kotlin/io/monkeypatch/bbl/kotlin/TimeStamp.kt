package io.monkeypatch.bbl.kotlin

import kotlin.js.Date

actual class TimeStamp actual constructor() {
    actual fun time(): Long {
        return Date().getTime().toLong()
    }
}