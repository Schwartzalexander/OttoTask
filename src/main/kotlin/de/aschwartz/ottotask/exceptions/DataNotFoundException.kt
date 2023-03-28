package de.aschwartz.ottotask.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class DataNotFoundException(var type: Class<*>, var id: Long) : RuntimeException() {

    override val message = "No data of type '${type.simpleName}' found for ID '$id'."

    override fun toString(): String {
        return message
    }

}