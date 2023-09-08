package de.gmuth.log

import org.junit.Test
import de.gmuth.log.Logger.Level

class AndroidLogAdapterTest {

    //@Test
    // java.lang.UnsatisfiedLinkError: android.util.Log.isLoggable(Ljava/lang/String;I)Z
    fun loggerTest() {
        Logger.createLogger = ::AndroidLogAdapter
        LoggerTest(javaClass).run()
    }
}