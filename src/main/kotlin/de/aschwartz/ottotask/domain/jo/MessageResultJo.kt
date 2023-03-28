package de.aschwartz.ottotask.domain.jo

/**
 *
 *
 * A JSON Object containing only a success flag and a message string. This is the mother of most other JO classes.
 *
 *
 *
 * @author Alexander Schwartz Created 08.11.2021
 */
open class MessageResultJo : ResultJo {
    /**
     * A string, that can contain a message
     */
    @JvmField
    var message: String? = null

    constructor(success: Boolean) : super(success) {}
    constructor(success: Boolean, message: String?) : super(success) {
        this.message = message
    }


}