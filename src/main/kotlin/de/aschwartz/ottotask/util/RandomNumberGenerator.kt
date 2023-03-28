package de.aschwartz.ottotask.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * A random number generator.
 */
object RandomNumberGenerator {


    /**
     * Generates and returns an Int random number.
     */
    fun generateInt(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return (start..end).random()
    }

    /**
     * Generates and returns a Long random number.
     */
    fun generateLong(start: Long, end: Long): Long {
        require(!(start > end || end - start + 1 > Long.MAX_VALUE)) { "Illegal Argument" }
        return (start..end).random()
    }

    fun generateBigDecimal(): BigDecimal {
        val dividend = generateLong(1000, 10000)
        val divisor = generateLong(1, 1000)
        return BigDecimal(dividend).divide(BigDecimal(divisor), 2, RoundingMode.HALF_UP)
    }

    fun generateBoolean(): Boolean {
        return generateInt(0, 1) % 2 == 0
    }
}