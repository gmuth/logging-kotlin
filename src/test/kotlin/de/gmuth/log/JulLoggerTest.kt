package de.gmuth.log

import de.gmuth.log.JUL.getLogger
import org.junit.Test
import java.util.logging.LogManager

class JulLoggerTest {

    @Test
    fun julLoggerTest() {

        // configure java util logging
        LogManager.getLogManager().readConfigurationResource()
        java.util.logging.Logger.getLogger("").handlers.forEach { println(it.javaClass.canonicalName) }

        getLogger {}.run {
            finest { "finest message" }
            finer { "finer message" }
            fine { "fine message" }
            config { "config message" }
            info { "info message" }
            warning { "warning message" }
            severe { "severe message" }

            //log(Level.SEVERE, RuntimeException("this is a runtime exception")) { "stacktrace" }
            info(RuntimeException("this is a runtime exception")) { "stacktrace with message" }
            warning(RuntimeException("this is a runtime exception without message"))

            // aliases
            trace { "trace --> finer message" }
            debug { "debug --> fine message" }
            warn { "warn --> warning message" }
            this.error { "error --> severe message" }
        }
    }
}