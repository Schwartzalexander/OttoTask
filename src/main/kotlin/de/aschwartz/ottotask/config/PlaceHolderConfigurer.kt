package de.aschwartz.ottotask.config

import de.aschwartz.ottotask.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.core.env.Environment
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * <P>
 * Custom PropertyPlaceholderConfigurer-implementation. Enables using placeholders in the Spring xml-configuration that are resolved reading a
 * properties-file.
</P> * <P></P>
 *
 * @author Alexander Schwartz Created 08.11.2021
 */
class PlaceHolderConfigurer : PropertyPlaceholderConfigurer() {
    /** The properties read from the file.  */
    private val properties = Properties()

    @Autowired
    private lateinit var environment: Environment

    /** The list of names of properties-files to be read.  */
    private var fileNames: List<String>? = null
    public override fun resolvePlaceholder(placeholder: String, props: Properties): String? {
        var value: String? = resolveFilePlaceholder(placeholder)
        if (value == null) {
            value = super.resolvePlaceholder(placeholder, props)
        }
//        if (value == null) {
//            return ""
//        }
        return value
    }

    /**
     * <P>
     * Resolve a property from the properties read from the given file.
    </P> * <P></P>
     *
     * @author Alexander Schwartz Created 08.11.2021
     * @param placeholder
     * The placeholder to resolve
     * @return The actual value to be used
     */
    private fun resolveFilePlaceholder(placeholder: String): String? {
        if (properties.isEmpty) {
            initializeProperties()
        }
        return properties.getProperty(placeholder)
    }

    /**
     * <P>
     * Reads the properties from the file.
    </P> * <P></P>
     *
     * @author Alexander Schwartz Created 08.11.2021
     */
    private fun initializeProperties() {
        // If not available, use the system environment variable spring.config.location or 'application.properties' as a fallback
        var propertiesFileLocation = System.getenv("--spring.config.location") ?: "application.properties"

        var configFilePath = propertiesFileLocation
        val f = File(configFilePath)
        logger.info("Path of properties file: ${f.absolutePath}")
        if (f.exists()) {
            try {
                val fIn = FileInputStream(f)
                properties.load(fIn)
                val propertyKeys: Set<Any> = properties.keys
                for (key in propertyKeys) {
                    if (!(key as String).contains("pass")) logger.info("Property $key = ${properties.getProperty(key)}.")
                }
                fIn.close()
                logger.info("Properties loaded from ${f.absolutePath}.")
            } catch (e: Exception) {
                logger.fatal(
                    "Caught exception in ${javaClass.name}.initializeProperties(). ${
                        StringUtils.formatExceptionMessage(
                            e
                        )
                    }", e
                )
            }
        } else logger.fatal("The properties file $configFilePath was not found.")
    }

    /**
     * <P>
     * Setter. Used by Spring to inject a list of filenames.
    </P> * <P></P>
     *
     * @author Alexander Schwartz Created 08.11.2021
     * @param fileNames
     * The list of properties-filenames
     */
    fun setFileNames(fileNames: List<String>?) {
        this.fileNames = fileNames
    }
}