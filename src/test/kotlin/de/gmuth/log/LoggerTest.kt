package de.gmuth.log

class LoggerTest(val clazz: Class<*>) {
    fun run(level: Level? = null) {
        Logger.getLogger(clazz.canonicalName).run {
            level?.let { logLevel = it }
            trace { "trace message" }
            debug { "debug message" }
            info { "info message" }
            warn { "warn message" }
            error { "error message" }
            error(RuntimeException("this is a runtime exception")) { "stacktrace" }
        }
    }
}