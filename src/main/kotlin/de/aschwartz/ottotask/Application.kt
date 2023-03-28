package de.aschwartz.ottotask

import de.aschwartz.ottotask.restcontrollers.PATH_REST_CONTROLLERS
import de.aschwartz.ottotask.restcontrollers.PATH_REST_CONTROLLER_HELP
import de.aschwartz.ottotask.restcontrollers.PATH_REST_CONTROLLER_STATUS
import de.aschwartz.ottotask.util.SpringContextHolder.Companion.applicationContext
import de.aschwartz.ottotask.util.StaticUtils
import de.aschwartz.ottotask.util.StaticUtils.Companion.determineHost
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ComponentScan(basePackages = ["de.aschwartz.ottotask"])
@EnableScheduling
open class Application : SpringBootServletInitializer() {

    companion object {
        val logger: Log = LogFactory.getLog(this.javaClass)

        /**
         * Prints some help messages about the REST interface.
         *
         * @param host hostname
         * @param port port
         */
        fun printHelpMessages(host: String?, port: String?) {
            val restPath = PATH_REST_CONTROLLERS
            val helpPath = PATH_REST_CONTROLLER_HELP
            val statusPath = PATH_REST_CONTROLLER_STATUS
            de.aschwartz.ottotask.Application.Companion.logger.info("Rest interface is running.")
            de.aschwartz.ottotask.Application.Companion.logger.info("Send GET request to http://$host:$port$restPath$statusPath to see the current status.")
            de.aschwartz.ottotask.Application.Companion.logger.info("Send GET request to http://$host:$port$restPath$helpPath to get further help.")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<de.aschwartz.ottotask.Application>(*args)

    val environment = applicationContext!!.environment
    val profile = StaticUtils.determineProfile(environment)
    val host = determineHost(environment)
    val port = environment.getProperty("local.server.port")

    de.aschwartz.ottotask.Application.Companion.logger.info("Active profile: $profile")
    de.aschwartz.ottotask.Application.Companion.logger.info("Spring Boot Archetype Backend application is running on http://$host:$port")
    de.aschwartz.ottotask.Application.Companion.printHelpMessages(host, port)
}

