package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.StreamHandler

class JulStdoutHandler() : StreamHandler(System.out, JulSimpleClassNameFormatter()) {

    init {
        level = Level.ALL
    }

    override fun publish(record: LogRecord?) {
        super.publish(record)
        flush()
    }

}