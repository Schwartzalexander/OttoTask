package de.aschwartz.ottotask.restcontrollers

import de.aschwartz.ottotask.domain.results.HttpRequestResult
import de.aschwartz.ottotask.domain.results.Result
import de.aschwartz.ottotask.enums.Outcome
import de.aschwartz.ottotask.exceptions.DataNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Handles exceptions for all RestControllers. Multiple exception types can be handled within handleConfig(..).
 */
@ControllerAdvice
open class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    /**
     * Handles exceptions for all RestControllers. Multiple exception types can added to this method.
     */
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class, DataNotFoundException::class])
    open fun handleConflict(e: RuntimeException, request: WebRequest?): ResponseEntity<Any>? {
        if (e is DataNotFoundException) {
            val result: Result = HttpRequestResult(Outcome.FAIL, e.message, HttpStatus.NOT_FOUND)
            return handleExceptionInternal(
                e, result,
                HttpHeaders(), HttpStatus.NOT_FOUND, request!!
            )
        }
        return handleExceptionInternal(
            e, "Unknown error",
            HttpHeaders(), HttpStatus.CONFLICT, request!!
        )
    }
}