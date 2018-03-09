package io.monkeypatch.bbl.kotlin

import platform.Foundation.*

actual fun currentTime(): Long = NSDate().timeIntervalSince1970.toLong() * 1000L
