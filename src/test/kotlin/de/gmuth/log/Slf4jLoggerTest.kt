package de.gmuth.log

import org.junit.Test

class Slf4jLoggerTest {

    @Test
    fun slf4jLoggerTest() {
        Logger.createLogger = ::Slf4jLogger
        LoggerTest(Slf4jAdapterTest::class.java).run()
    }
}