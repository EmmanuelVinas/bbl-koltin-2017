package io.monkeypatch.bbl.kotlin

import org.amshove.kluent.`should be`
import org.junit.Test

class TimeStampTest {

    @Test
    fun `TimeStamp should be initialized`() {
        val timeStamp = TimeStamp()
        timeStamp.timestamp `should be` timeStamp.time()
    }


}
