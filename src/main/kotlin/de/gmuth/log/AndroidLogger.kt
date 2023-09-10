package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import android.util.Log
import de.gmuth.log.Logger.Level.*

// https://developer.android.com/reference/android/util/Log
class AndroidLogger(name: String) : Logger(name) {

    override fun isEnabled(level: Level) =
        when (level) {
            OFF -> false
            TRACE -> Log.isLoggable(name, Log.VERBOSE)
            DEBUG -> Log.isLoggable(name, Log.DEBUG)
            INFO -> Log.isLoggable(name, Log.INFO)
            WARN -> Log.isLoggable(name, Log.WARN)
            ERROR -> Log.isLoggable(name, Log.ERROR)
        }

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
}