package de.aschwartz.ottotask.scheduler

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

private const val ONE_MINUTES_IN_MILLIS = 60000L;
private const val ONE_HOUR_IN_MILLIS = 3600000L;

@Component
class Scheduler(
    private val environment: Environment,
) {

//    @Scheduled(cron = "\${scheduler.cron.expression.example-cron}")
//    @Throws(Exception::class)
//    fun exampleCron() {
//        // Add scheduler.cron.expression.example-scheduled to application.properties
//    }
//
//    @Scheduled(fixedDelay = ONE_MINUTES_IN_MILLIS)
//    @Throws(Exception::class)
//    fun exampleDelay() {
//    }

    private val logger: Log = LogFactory.getLog(this.javaClass)
}