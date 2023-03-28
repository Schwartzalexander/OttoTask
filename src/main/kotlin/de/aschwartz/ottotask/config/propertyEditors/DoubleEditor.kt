package de.aschwartz.ottotask.config.propertyEditors

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.beans.PropertyEditorSupport
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

class DoubleEditor : PropertyEditorSupport() {
    override fun setAsText(text: String) {
        val formatter = NumberFormat.getNumberInstance(Locale.US)
        try {
            val number = formatter.parse(text)
            val value = java.lang.Double.valueOf(number.toDouble())
            setValue(value)
        } catch (e: ParseException) {
            logger.fatal("Parsing error in class ${this.javaClass.name}. $text cannot be parsed as a number. ", e)
            value = null
        }
    }

    // TODO: I tried all reasonable available methods in PropertyEditorSupport. None of them was called, when submitting a form with a double value to
    // a controller. The controller can't interpret the input "1,000".
    override fun setValue(value: Any?) {
        if (value != null) {
            val formatterDE = NumberFormat.getNumberInstance(Locale.GERMAN)
            val formatterUS = NumberFormat.getNumberInstance(Locale.US)
            val valueString = formatterDE.format(value)
            val number: Number?
            try {
                number = formatterUS.parse(valueString)
                super.setValue(number)
            } catch (e: ParseException) {
                logger.fatal("Parsing error in class ${this.javaClass.name}. $valueString cannot be parsed as a number. ", e)
            }
        }
        super.setValue(value)
    }

    private val logger: Log = LogFactory.getLog(this.javaClass)
}