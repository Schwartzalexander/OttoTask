package de.aschwartz.ottotask.domain.jo

/**
 * <P>
 * The simplest of all JSON Objects containing only a success flag.
</P> * <P></P>
 *
 * @author Alexander Schwartz Created 08.11.2021
 */
open class ResultJo
    (
    /**
     * Success flag. True is successful, false otherwise
     */
    var isSuccess: Boolean
) {
}