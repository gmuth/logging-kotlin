package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

object NoOperationLogger : Logger("") {
    override fun isEnabled(level: Level) = false
    override fun publish(logEvent: LogEvent) = Unit
}