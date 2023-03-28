package de.aschwartz.ottotask.bootstrap

import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.util.logging.Logger


/**
 * Code to be executed on startup
 */
@Component
@ComponentScan(basePackages = ["services"])
class Bootstrap : ApplicationListener<ContextRefreshedEvent?> {

    private val logger = Logger.getLogger(Bootstrap::class.java.name)

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        logger.info("Application starting.")
    }

}