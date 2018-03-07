package io.monkeypatch.bbl.kotlin

import java.util.*

actual class TimeStamp actual constructor() {
    private val timeStamp: Long by lazy {
        Date().time
    }

    actual fun time() = timeStamp
}