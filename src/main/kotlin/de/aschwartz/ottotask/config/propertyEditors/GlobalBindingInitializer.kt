package de.aschwartz.ottotask.config.propertyEditors

import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.InitBinder
import java.math.BigDecimal
import java.util.*

@ControllerAdvice
class GlobalBindingInitializer {
    @InitBinder
    fun binder(binder: WebDataBinder) {
        // Initialize a global InitBinder for dates instead of cloning its code in every Controller
        binder.registerCustomEditor(Date::class.java, DateEditor())
        binder.registerCustomEditor(Double::class.java, DoubleEditor())
        binder.registerCustomEditor(BigDecimal::class.java, BigDecimalEditor())
    }
}