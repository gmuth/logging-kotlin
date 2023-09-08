package de.gmuth.log

import org.junit.Test
import de.gmuth.log.Logger.Level

class ConsoleLoggerTest {

    @Test
    fun consoleLoggerTest() {
        ConsoleLogger.simpleClassName = true
        ConsoleLogger.defaultLogLevel = Level.TRACE

        Logger.createLogger = ::ConsoleLogger
        LoggerTest(javaClass).run()
    }
}