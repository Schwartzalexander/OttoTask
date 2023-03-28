package de.aschwartz.ottotask.config.propertyEditors

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.beans.PropertyEditorSupport
import java.math.BigDecimal
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

class BigDecimalEditor : PropertyEditorSupport() {
    override fun setAsText(text: String) {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        try {
            val number = formatter.parse(text)
            val value = BigDecimal.valueOf(number.toDouble())
            setValue(value)
        } catch (e: ParseException) {
            logger.fatal("Parsing error in class ${this.javaClass.name}. $text cannot be parsed as a number. ", e)
            value = null
        }
    }

    override fun getAsText(): String {
        if (value == null)
            return ""
        var s = ""
        val text = (value as BigDecimal).toPlainString()
        if (text != null) {
            val formatterDE = NumberFormat.getNumberInstance(Locale.GERMAN)
            val formatterUS = NumberFormat.getNumberInstance(Locale.US)
            val number = try {
                formatterUS.parse(text)
            } catch (e: ParseException) {
                logger.fatal("Parsing error in class ${this.javaClass.name}. $text cannot be parsed as a number. ", e)
                return text
            }
            val value = BigDecimal.valueOf(number?.toDouble() ?: 0.0)
            s = formatterDE.format(value)
        }
        return s
    }

    private val logger: Log = LogFactory.getLog(this.javaClass)
}