package de.aschwartz.ottotask.controllers

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.core.env.Environment
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping


@Controller
open class IndexController(
    private val environment: Environment,
) {

    @RequestMapping("/")
    open fun index(model: Model): String {
        addEnvironmentPropertiesToModel(model)
        return "index"
    }

    fun addEnvironmentPropertiesToModel(model: Model) {
        model.addAttribute("property_spring_profiles_active", prop("spring.profiles.active"))
        model.addAttribute("property_server_hostname", prop("server.hostname"))
        model.addAttribute("property_server_port", prop("server.port"))
        model.addAttribute("property_server_session_timeout", prop("server.session.timeout"))
        model.addAttribute("property_server_error_include_stacktrace", prop("server.error.include-stacktrace"))
    }


    private fun prop(name: String): String? {
        return environment.getProperty(name)
    }

    private val logger: Log = LogFactory.getLog(this.javaClass)
}
