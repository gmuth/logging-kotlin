package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

class LogEvent(
    val level: Level,
    val supplyMessage: MessageSupplier,
    val throwable: Throwable?
) {
    val messageString by lazy { supplyMessage()?.toString() }
}