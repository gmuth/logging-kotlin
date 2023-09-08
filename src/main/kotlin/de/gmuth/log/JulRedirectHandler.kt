package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import de.gmuth.log.Logger.Companion.getLogger
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

// redirect java util logging messages to de.gmuth.log.Logging
object JulRedirectHandler : Handler() {

    override fun publish(logRecord: LogRecord) = logRecord.run {
        getLogger(loggerName).log(level.toLoggerLevel(), thrown) { message }
    }

    override fun flush() = Unit
    override fun close() = Unit

    fun addToJulLogger(name: String = "", julLevel: Level = Level.ALL) {
        Logger.getLogger(name).run {
            if (!handlers.contains(JulRedirectHandler)) addHandler(JulRedirectHandler)
            level = julLevel
        }
    }
}