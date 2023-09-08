package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import java.util.logging.LogManager
import java.util.logging.LogRecord
import java.util.logging.SimpleFormatter

class JulSimpleClassNameFormatter(
    val simpleClassName: Boolean = getProperty("simpleClassName")?.toBooleanStrict() ?: true
) : SimpleFormatter() {

    companion object {
        private val logManager by lazy { LogManager.getLogManager() }
        private fun getProperty(name: String): String? =
            logManager.getProperty("${JulSimpleClassNameFormatter::class.java.canonicalName}.$name")
    }

    override fun format(logRecord: LogRecord?) = super.format(
        logRecord.apply {
            if (this != null && simpleClassName) loggerName = loggerName.substringAfterLast(".")
        }
    )
}