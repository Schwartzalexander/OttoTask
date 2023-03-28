package de.aschwartz.ottotask.util

import de.aschwartz.ottotask.domain.DurationCompat
import de.aschwartz.ottotask.exceptions.OttoException
import org.apache.commons.lang3.time.StopWatch
import org.apache.commons.logging.LogFactory
import org.springframework.core.env.Environment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.UnknownHostException
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt


class StaticUtils {

    companion object {

        private val logger = LogFactory.getLog(StaticUtils::class.java)


        fun determineHost(environment: Environment): String {
            val hostname = environment.getProperty("server.hostname")
            if (hostname != null && !hostname.isEmpty())
                return hostname;
            return try {
                val hostAddress = InetAddress.getLocalHost().hostAddress
                hostAddress
            } catch (e: UnknownHostException) {
                "localhost";
            }
        }

        /**
         * Determines the active spring profile set in application.properties. It should be either 'spring.profiles.active=qa' or 'spring.profiles.active=prod'.
         * @param environment environment object
         * @return 'qa', 'prod' or whatever is set in application.properties
         * @exception aschwartz.ottotaskException If active spring profile is not set  in application.properties.
         */
        fun determineProfile(environment: Environment): String {
            val profile = environment.getProperty("spring.profiles.active")
            if (profile != null && !profile.isEmpty())
                return profile;
            throw OttoException("Spring profile is not set. Please add the line 'spring.profiles.active=qa' or 'spring.profiles.active=prod' to application.properties.")
        }


        /**
         * Resets and starts the given stopWatch.
         * @param stopWatch to be restarted.
         */
        fun restartStopWatch(stopWatch: StopWatch) {
            stopWatch.reset()
            stopWatch.start()
        }

        /**
         * Formats the given date time and returns the formatted string.
         * @param dateTime date and time to be formatted
         * @param formatter formatter to be used. If null, a new formatter is created. If you want to format a large number of date times, please create your
         * own formatter outside this function to avoid creating a new instance each time.
         */
        fun formatDateAndTime(dateTime: LocalDateTime, formatter: DateTimeFormatter?): String {
            if (formatter == null) {
                val newFormatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT)
                return newFormatter.format(dateTime)
            }
            return formatter.format(dateTime)
        }

        /**
         * Formats the given duration as a human-readable string.
         * @param duration duration to be formatted
         * @return duration string, e.g. "04:11:20"
         */
        fun formatDurationShort(duration: Duration): String {
            return String.format(
                Constants.TIME_FORMAT_SHORT,
                DurationCompat(duration).toHoursPart(),
                DurationCompat(duration).toMinutesPart(),
                DurationCompat(duration).toSecondsPart(),
            )
        }

        /**
         * Formats the given duration as a human-readable string.
         * @param duration duration to be formatted
         * @return duration string, e.g. "0d 04:11:20"
         */
        fun formatDuration(duration: Duration): String {
            return String.format(
                Constants.TIME_FORMAT,
                duration.toDays(),
                DurationCompat(duration).toHoursPart(),
                DurationCompat(duration).toMinutesPart(),
                DurationCompat(duration).toSecondsPart(),
            )
        }

        /**
         * Executes the given bash command. Can handle complex bash commands including multiple executions (; | && ||), quotes, expansions ($), escapes (\),
         * e.g.: "cd /abc/def; mv ghi 'older ghi '$(whoami)"
         * @param command bash command
         *
         */
        fun executeBashCommand(command: String): String {
            try {
                val resultStringBuilder = StringBuilder()
                if (isWindows()) {
                    val builder = ProcessBuilder("cmd.exe", "/c", command)
                    builder.redirectErrorStream(true)
                    val p = builder.start()
                    val r = BufferedReader(InputStreamReader(p.inputStream))
                    var line: String?
                    while (true) {
                        line = r.readLine()
                        if (line == null) {
                            break
                        }
                        logger.debug(line)
                        resultStringBuilder.append("$line\n")
                    }
                } else {
                    val p = Runtime.getRuntime().exec(command)
                    p.waitFor()
                    val reader = BufferedReader(InputStreamReader(p.inputStream))

                    while (true) {
                        val line = reader.readLine() ?: break
                        resultStringBuilder.append("$line\n")
                        logger.debug(line)
                    }
                }
                return resultStringBuilder.toString()

            } catch (e: Exception) {
                return "Error executing command \"$command\": ${StringUtils.formatExceptionMessage(e)}"
            }
        }

        /**
         * Loads the bash command with the given property name and executes it
         * @param propertyName name of the property containing the bash command in application.properties
         * @param environment environment used for accessing properties
         */
        fun executeBashCommandForProperty(propertyName: String, environment: Environment): String? {
            val command = environment.getProperty(propertyName)

            if (command != null && command.isNotEmpty()) {
                logger.info("Executing $propertyName: $command")
                val result = executeBashCommand(command)
                logger.info(result)
                return result
            }

            val failedResult = "Tying to execute $propertyName, but no command is defined for it."
            logger.info(failedResult)
            return failedResult
        }

        fun isWindows() = System.getProperty("os.name")?.lowercase(Locale.getDefault())?.startsWith("windows") ?: false

        /**
         * Rounds the given number to the given amount of decimal places.
         * @param number number to be rounded
         * @param decimalPlaces number of decimal places to be rounded to, default 2
         * @return rounded number
         */
        fun round(number: Double, decimalPlaces: Int = 2): Double {
            val factor = 10.0.pow(decimalPlaces)
            return (number * factor).roundToInt() / factor
        }


        /**
         * Determines the current hour of the day.
         * @return value 0-23
         */
        fun getCurrentHour() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    }
}
