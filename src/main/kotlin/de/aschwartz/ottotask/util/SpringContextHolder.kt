package de.aschwartz.ottotask.util

import org.springframework.beans.BeansException
import org.springframework.beans.factory.DisposableBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

/**
 * The spring context holder is a hack, that makes the spring context available in a static context. This way, for instance environment properties can be accessed.
 * Use with care.
 *
 * Java Usage example:
 *
 *  Environment environment = SpringContextHolder.Companion.getApplicationContext().getEnvironment();
 *  final String port = environment.getProperty("local.server.port");
 */
@Service
@Lazy(false)
class SpringContextHolder : ApplicationContextAware, DisposableBean {
    @Throws(Exception::class)
    override fun destroy() {
        clearHolder()
    }

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        Companion.applicationContext = applicationContext
    }

    companion object {
        var applicationContext: ApplicationContext? = null
            private set

        fun clearHolder() {
            applicationContext = null
        }
    }
}