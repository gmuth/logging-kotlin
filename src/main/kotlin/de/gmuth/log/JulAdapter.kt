package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import de.gmuth.log.Logger.Level.*

// forward log messages to java util logging
// https://docs.oracle.com/javase/8/docs/technotes/guides/logging/overview.html
class JulAdapter(name: String) : Logger(name) {

    private val julLogger = java.util.logging.Logger.getLogger(name)

    override var logLevel: Level
        get() = julLogger.level.toLoggerLevel()
        set(value) {
            julLogger.level = value.toJulLevel()
        }

    override fun isEnabled(level: Level) =
        julLogger.isLoggable(level.toJulLevel())

    override fun publish(logEvent: LogEvent) = logEvent.run {
        julLogger.log(level.toJulLevel(), messageString, throwable)
    }

    fun Level.toJulLevel(): java.util.logging.Level = when (this) {
        OFF -> java.util.logging.Level.OFF
        TRACE -> java.util.logging.Level.FINER
        DEBUG -> java.util.logging.Level.FINE
        INFO -> java.util.logging.Level.INFO
        WARN -> java.util.logging.Level.WARNING
        ERROR -> java.util.logging.Level.SEVERE
    }
}

// java util logging extension
fun java.util.logging.Level.toLoggerLevel(): Logger.Level = when (this) {
    java.util.logging.Level.OFF -> OFF
    java.util.logging.Level.FINER, java.util.logging.Level.FINEST, java.util.logging.Level.ALL -> TRACE
    java.util.logging.Level.FINE -> DEBUG
    java.util.logging.Level.INFO, java.util.logging.Level.CONFIG -> INFO
    java.util.logging.Level.WARNING -> WARN
    java.util.logging.Level.SEVERE -> ERROR
    else -> throw IllegalArgumentException("unknown Level $this")
}