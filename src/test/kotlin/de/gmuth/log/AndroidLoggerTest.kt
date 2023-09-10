package de.gmuth.log

import org.junit.Test
import de.gmuth.log.Logger.Level

class AndroidLoggerTest {

    //@Test
    // java.lang.UnsatisfiedLinkError: android.util.Log.isLoggable(Ljava/lang/String;I)Z
    fun androidLoggerTest() {
        Logger.createLogger = ::AndroidLogger
        LoggerTest(javaClass).run()
    }
}