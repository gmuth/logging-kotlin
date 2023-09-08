package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import de.gmuth.log.Logger.Level.*

typealias MessageSupplier = () -> Any?

abstract class Logger(val name: String) {

    companion object {

        // logger storage
        internal val loggerMap: MutableMap<String, Logger> = mutableMapOf()

        // logger factory method/lambda
        @JvmStatic
        var createLogger: ((name: String) -> Logger)? = null

        @JvmStatic
        fun getLogger(name: String) =
            if (createLogger == null)
                NoOperationLogger
            else
                loggerMap[name] ?: createLogger!!(name).apply {
                    loggerMap[name] = this
                }

        @JvmStatic
        fun getLogger(noOperation: () -> Unit) =
            getLogger(noOperation.javaClass.enclosingClass.name)
    }

    // ----------- Level -----------

    enum class Level {
        OFF,
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    // ----------- Logger -----------

    open var logLevel = OFF
    open fun isEnabled(level: Level) = logLevel != OFF && logLevel <= level
    abstract fun publish(level: Level, throwable: Throwable? = null, messageString: String?)

    @JvmOverloads
    fun trace(throwable: Throwable? = null, messageSupplier: MessageSupplier = { "" }) =
        log(TRACE, throwable, messageSupplier)

    @JvmOverloads
    fun debug(throwable: Throwable? = null, messageSupplier: MessageSupplier = { "" }) =
        log(DEBUG, throwable, messageSupplier)

    @JvmOverloads
    fun info(throwable: Throwable? = null, messageSupplier: MessageSupplier = { "" }) =
        log(INFO, throwable, messageSupplier)

    @JvmOverloads
    fun warn(throwable: Throwable? = null, messageSupplier: MessageSupplier = { "" }) =
        log(WARN, throwable, messageSupplier)

    @JvmOverloads
    fun error(throwable: Throwable? = null, messageSupplier: MessageSupplier = { "" }) =
        log(ERROR, throwable, messageSupplier)

    @JvmOverloads
    fun log(level: Level, throwable: Throwable? = null, produceMessage: MessageSupplier) {
        if (isEnabled(level)) publish(level, throwable, produceMessage()?.toString())
    }

    @JvmOverloads
    fun logWithCauseMessages(throwable: Throwable, level: Level = ERROR) {
        throwable.cause?.let { logWithCauseMessages(it, level) }
        log(level) { "${throwable.javaClass.name}: ${throwable.message}" }
    }

}