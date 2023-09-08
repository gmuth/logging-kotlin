package de.gmuth.log

import org.junit.Test

class NoOperationLoggerTest {

    @Test
    fun noOperationLoggerTest() {
        Logger.createLogger = null
        LoggerTest(javaClass).run()
    }
}