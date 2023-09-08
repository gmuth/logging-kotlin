package de.gmuth.log

import org.junit.Test

class Slf4jAdapterTest {

    @Test
    fun slf4jAdapterTest() {
        Logger.createLogger = ::Slf4jAdapter
        LoggerTest(Slf4jAdapterTest::class.java).run()
    }
}