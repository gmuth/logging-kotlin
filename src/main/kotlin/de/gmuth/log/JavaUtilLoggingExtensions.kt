package de.gmuth.log

/**
 * Copyright (c) 2023 Gerhard Muth
 */

import java.io.FileInputStream
import java.util.function.Supplier
import java.util.logging.Level.*
import java.util.logging.LogManager
import java.util.logging.Logger

object JUL {
    fun getLogger(noOperation: () -> Unit) =
        Logger.getLogger(noOperation.javaClass.enclosingClass.name)
}

fun LogManager.readConfigurationFile(filename: String = "logging.properties") =
    LogManager.getLogManager().readConfiguration(FileInputStream(filename));

fun LogManager.readConfigurationResource(resource: String = "/logging.properties") =
    LogManager.getLogManager().readConfiguration(JUL::class.java.getResourceAsStream(resource));

fun Logger.finest(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(FINEST, thrown, msgSupplier)

fun Logger.finer(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(FINER, thrown, msgSupplier)

fun Logger.fine(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(FINE, thrown, msgSupplier)

fun Logger.config(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(CONFIG, thrown, msgSupplier)

fun Logger.info(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(INFO, thrown, msgSupplier)

fun Logger.warning(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(WARNING, thrown, msgSupplier)

fun Logger.severe(thrown: Throwable, msgSupplier: Supplier<String?> = Supplier { "" }) =
    log(SEVERE, thrown, msgSupplier)