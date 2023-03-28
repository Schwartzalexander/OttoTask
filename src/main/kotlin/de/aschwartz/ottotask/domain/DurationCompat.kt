package de.aschwartz.ottotask.domain

import java.time.Duration

class DurationCompat(private val duration: Duration) {
    fun toSecondsPart() = duration.seconds % 60
    fun toMinutesPart() = duration.toMinutes() % 60
    fun toHoursPart() = duration.toHours() % 24
}