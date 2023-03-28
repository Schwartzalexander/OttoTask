package de.aschwartz.ottotask.util

import kotlin.math.abs

object NumberUtils {
    /**
     * <P>
     * Compares two float variables and returns true, if they are nearly equal, i.e. the difference is smaller than the given error margin epsilon.
    </P> * <P></P>
     * <P>
     * Source: http://floating-point-gui.de/errors/comparison/
    </P> * <P></P>
     *
     * @param a
     * first float number
     * @param b
     * second float number
     * @param epsilon
     * error margin
     * @return true, if nearly equal, false otherwise
     */
    @JvmStatic
    fun areNearlyEqual(a: Float, b: Float, epsilon: Float): Boolean {
        val absA = abs(a)
        val absB = abs(b)
        val diff = abs(a - b)
        return if (a == b) { // shortcut, handles infinities
            true
        } else if (a == 0f || b == 0f || diff < java.lang.Float.MIN_NORMAL) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            diff < epsilon * java.lang.Float.MIN_NORMAL
        } else { // use relative error
            diff / (absA + absB).coerceAtMost(Float.MAX_VALUE) < epsilon
        }
    }

    /**
     * <P>
     * Checks, if the given number is nearly zero, i.e the difference to zero is smaller than the given error margin epsilon.
    </P> * <P></P>
     *
     * @param number
     * float number to be checked
     * @param epsilon
     * error margin
     * @return true, if nearly zero, false otherwise
     */
    @JvmStatic
    fun isNearlyZero(number: Float, epsilon: Float): Boolean {
        return areNearlyEqual(number, 0f, epsilon)
    }

    /**
     * <P>
     * Compares two double variables and returns true, if they are nearly equal, i.e. the difference is smaller than the given error margin epsilon.
    </P> * <P></P>
     * <P>
     * Source: http://floating-point-gui.de/errors/comparison/
    </P> * <P></P>
     *
     * @param a
     * first double number
     * @param b
     * second double number
     * @param epsilon
     * error margin
     * @return true, if nearly equal, false otherwise
     */
    @JvmStatic
    fun areNearlyEqual(a: Double, b: Double, epsilon: Double): Boolean {
        val absA = abs(a)
        val absB = abs(b)
        val diff = abs(a - b)
        return if (a == b) { // shortcut, handles infinities
            true
        } else if (a == 0.0 || b == 0.0 || diff < java.lang.Double.MIN_NORMAL) {
            // a or b is zero or both are extremely close to it
            // relative error is less meaningful here
            diff < epsilon * java.lang.Double.MIN_NORMAL
        } else { // use relative error
            diff / (absA + absB).coerceAtMost(Double.MAX_VALUE) < epsilon
        }
    }

    /**
     * <P>
     * Checks, if the given number is nearly zero, i.e the difference to zero is smaller than the given error margin epsilon.
    </P> * <P></P>
     *
     * @param number
     * double number to be checked
     * @param epsilon
     * error margin
     * @return true, if nearly zero, false otherwise
     */
    @JvmStatic
    fun isNearlyZero(number: Double, epsilon: Double): Boolean {
        return areNearlyEqual(number, 0.0, epsilon)
    }


    @JvmStatic
    fun getRandomLongNumber(start: Long, end: Long): Long {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    @JvmStatic
    fun getRandomIntNumber(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

    /**
     * Returns true in 50 percent of cases, false otherwise.
     * @return true with 50% probability, false otherwise.
     */
    fun if50PercentChance(): Boolean {
        return (0..1).random() == 0;
    }
}