package de.aschwartz.ottotask.domain.jo

/**
 *
 *
 * A JSON Object containing a success flag, a message string and a custom payload.
 *
 *
 *
 * @author Alexander Schwartz Created 08.11.2021
 */
class PayloadResultJo<T> : MessageResultJo {
    var payload: T? = null

    constructor(success: Boolean, message: String?) : super(success, message) {}
    constructor(success: Boolean, payload: T, message: String?) : super(success, message) {
        this.payload = payload
    }

}