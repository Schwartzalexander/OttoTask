package de.aschwartz.ottotask.config.propertyEditors

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.beans.PropertyEditorSupport
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateEditor : PropertyEditorSupport() {
    override fun setAsText(value: String) {
        try {
            setValue(SimpleDateFormat("dd.MM.yyyy").parse(value))
        } catch (e: ParseException) {

            logger.fatal("Parsing error in class ${this.javaClass.name}. $value cannot be parsed as a date.", e)

            setValue(null)
        }
    }

    override fun getAsText(): String {
        var s = ""
        if (value != null) {
            s = SimpleDateFormat("dd.MM.yyyy").format(value as Date)
        }
        return s
    }

    private val logger: Log = LogFactory.getLog(this.javaClass)
}