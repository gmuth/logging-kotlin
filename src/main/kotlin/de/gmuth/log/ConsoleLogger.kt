package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import java.io.PrintWriter
import java.time.LocalDateTime.now

class ConsoleLogger(name: String) : Logger(name) {

    companion object {
        var defaultLogLevel = Level.INFO

        // %1=timestamp, %2=loggerName, %3=level, %4=message
        var format: String = "%1\$tT.%1\$tL %2\$-30s %3\$-10s %4\$s%n"
        var simpleClassName = true
    }

    init {
        logLevel = defaultLogLevel
    }

    override fun publish(level: Level, throwable: Throwable?, messageString: String?) {
        val loggerName = if (simpleClassName) name.substringAfterLast(".") else name
        print(format.format(now(), loggerName, level, messageString))
        throwable?.printStackTrace(PrintWriter(System.out, true))
    }
}