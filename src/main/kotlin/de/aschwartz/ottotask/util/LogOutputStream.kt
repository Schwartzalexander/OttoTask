package de.aschwartz.ottotask.util

import org.apache.commons.logging.Log
import java.io.OutputStream
import java.util.logging.Level

/**
 * This class logs all bytes written to it as output stream with a specified logging level.
 * Constructor: Creates a new log output stream which logs bytes to the specified logger with the specified
 * level.
 *
 * @param logger the logger, where to log the written bytes
 * @param level the level
 * @version 1.0
 */
class LogOutputStream(private val logger: Log, private val level: Level) : OutputStream() {

    /**
     * The internal memory for the written bytes.
     */
    private var mem: String

    /**
     * Writes a byte to the output stream. This method flushes automatically at the end of a line.
     */
    override fun write(b: Int) {
        val bytes = ByteArray(1)
        bytes[0] = (b and 0xff).toByte()
        mem = mem + String(bytes)
        if (mem.endsWith("\n")) {
            mem = mem.substring(0, mem.length - 1)
            flush()
        }
    }

    /**
     * Flushes the output stream.
     */
    override fun flush() {
        if (level == Level.FINE)
            logger.debug(mem)
        if (level == Level.INFO)
            logger.info(mem)
        if (level == Level.WARNING)
            logger.warn(mem)
        if (level == Level.SEVERE)
            logger.fatal(mem)
        mem = ""
    }

    init {
        mem = ""
    }
}