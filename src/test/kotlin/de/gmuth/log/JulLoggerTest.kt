package de.gmuth.log

import org.junit.Test
import java.util.logging.LogManager

class JulLoggerTest {

    @Test
    fun julLoggerTest() {
        LogManager.getLogManager().readConfigurationResource()
        println("configured handlers:")
        java.util.logging.Logger.getLogger("").handlers.forEach { println(it.javaClass.canonicalName) }

        Logger.createLogger = ::JulLogger
        LoggerTest(javaClass).run()
    }
}