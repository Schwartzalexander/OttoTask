package de.aschwartz.ottotask.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    /**
     * Converts a time in seconds into a String formatted "HH:mm:ss". If you call this method often, use formatMillisAsHHmmss(long, SimpleDateFormat)
     * instead.
     *
     * @param millis
     * milliseconds to be formatted
     * @return String formatted "HH:mm:ss"
     */
    @JvmStatic
    fun formatMillisAsHHmmss(millis: Long): String {
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("HH:mm:ss")
        df.timeZone = tz
        return df.format(Date(millis))
    }

    /**
     * Converts a time in seconds into a String formatted "mm:ss". If you call this method often, use formatMillisAsTime(long, SimpleDateFormat)
     * instead.
     *
     * @param millis
     * milliseconds to be formatted
     * @return String formatted "mm:ss"
     */
    @JvmStatic
    fun formatMillisAsmmss(millis: Long): String {
        val tz = TimeZone.getTimeZone("UTC")
        val df = SimpleDateFormat("mm:ss")
        df.timeZone = tz
        return df.format(Date(millis))
    }

    /**
     * Converts a time in seconds into a String formatted "mm:ss".
     *
     * @param millis
     * milliseconds to be formatted
     * @param sdf
     * SimpleDateFormat
     * @return String formatted with sdf
     */
    @JvmStatic
    fun formatMillisAsTime(millis: Long, sdf: SimpleDateFormat): String {
        return sdf.format(Date(millis))
    }
}