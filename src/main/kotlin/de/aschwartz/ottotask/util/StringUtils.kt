package de.aschwartz.ottotask.util

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object StringUtils {
    const val UTF8 = "UTF-8"

    /**
     * <P>
     * Transforms the first char of the given string to lower case and leaves the rests as it is. Returns the resulting string. If no string is given
     * (null or empty), an empty string is returned.
    </P> * <P></P>
     *
     * @author Alexander Schwartz Created 08.11.2021
     * @param string
     * some string
     * @return same string with lower case first character
     */
    fun firstCharToLowerCase(string: String?): String {

        if (string == null || string.isEmpty()) return ""
        val c = string.toCharArray()
        c[0] = Character.toLowerCase(c[0])
        return String(c)
    }

    /**
     * <P>
     * Converts an input String to a bytes array
    </P> * <P></P>
     *
     * @author Alexander Schwartz Created 08.11.2021
     * @param input
     * some String
     * @return bytes array
     */
    fun getBytes(input: String?): ByteArray {
        return input?.toByteArray(StandardCharsets.UTF_8) ?: ByteArray(0)
    }

    /**
     * <P>
     * Encodes the given URL
    </P> * <P></P>
     *
     * @author Alexander Schwartz Created 08.11.2021
     * @param input
     * @return Encoded URL String
     * @throws UnsupportedEncodingException
     */
    @Throws(UnsupportedEncodingException::class)
    fun urlEncode(input: String?): String {
        return URLEncoder.encode(input, UTF8)
    }

    fun formatExceptionMessage(e: Exception): String {
        val stringBuilder = StringBuilder()
        if (e.message != null)
            stringBuilder.append("Message: ${e.message}")
        if (e.message != null && e.cause != null)
            stringBuilder.append(" - ")
        if (e.cause != null)
            stringBuilder.append("Cause: ${e.cause}")
        return stringBuilder.toString()
    }

}