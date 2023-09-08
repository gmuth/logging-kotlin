package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import de.gmuth.log.Logger.Level.*

// http://www.slf4j.org
class Slf4jAdapter(name: String) : Logger(name) {

    private val slf4jLogger = org.slf4j.LoggerFactory.getLogger(name)

    override fun isEnabled(level: Level) = when (level) {
        OFF -> false
        TRACE -> slf4jLogger.isTraceEnabled
        DEBUG -> slf4jLogger.isDebugEnabled
        INFO -> slf4jLogger.isInfoEnabled
        WARN -> slf4jLogger.isWarnEnabled
        ERROR -> slf4jLogger.isErrorEnabled
    }

    override fun publish(level: Level, throwable: Throwable?, messageString: String?) {
        when (level) {
            OFF -> Unit // don't publish anything
            TRACE -> slf4jLogger.trace(messageString, throwable)
            DEBUG -> slf4jLogger.debug(messageString, throwable)
            INFO -> slf4jLogger.info(messageString, throwable)
            WARN -> slf4jLogger.warn(messageString, throwable)
            ERROR -> slf4jLogger.error(messageString, throwable)
        }
    }
}