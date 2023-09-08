package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import android.util.Log
import de.gmuth.log.Logger.Level.*

// https://developer.android.com/reference/android/util/Log
class AndroidLogAdapter(name: String) : Logger(name) {

    override fun isEnabled(level: Level) =
        level != OFF && Log.isLoggable(name, level.toInt())

    override fun publish(level: Level, throwable: Throwable?, messageString: String?) {
        when (level) {
            OFF -> Unit // don't publish anything
            TRACE -> Log.v(name, messageString, throwable)
            DEBUG -> Log.d(name, messageString, throwable)
            INFO -> Log.i(name, messageString, throwable)
            WARN -> Log.w(name, messageString, throwable)
            ERROR -> Log.e(name, messageString, throwable)
        }
    }

    private fun Level.toInt() = when (this) {
        TRACE -> Log.VERBOSE
        DEBUG -> Log.DEBUG
        INFO -> Log.INFO
        WARN -> Log.WARN
        ERROR -> Log.ERROR
        else -> throw IllegalArgumentException(this.toString())
    }
}