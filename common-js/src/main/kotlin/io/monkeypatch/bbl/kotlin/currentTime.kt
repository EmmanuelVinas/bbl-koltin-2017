package io.monkeypatch.bbl.kotlin

import kotlin.js.Date

actual fun currentTime(): Long = Date().getTime().toLong()