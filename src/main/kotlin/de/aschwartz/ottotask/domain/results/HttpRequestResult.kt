package de.aschwartz.ottotask.domain.results

import de.aschwartz.ottotask.enums.Outcome
import org.springframework.http.HttpStatus

open class HttpRequestResult(outcome: Outcome, message: String, var httpStatus: HttpStatus) : MessageResult(outcome, message)