package de.aschwartz.ottotask.services

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class AppInitService(
    private var environment: Environment,
) {

    private val logger: Log = LogFactory.getLog(this.javaClass)

    @EventListener(ApplicationReadyEvent::class)
    fun initialize() {
        logger.info("Initializing App")
    }

}