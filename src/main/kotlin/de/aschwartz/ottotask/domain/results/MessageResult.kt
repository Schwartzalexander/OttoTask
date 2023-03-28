package de.aschwartz.ottotask.domain.results

import de.aschwartz.ottotask.enums.Outcome


open class MessageResult(override var outcome: Outcome, var message: String) : Result(outcome) {

    companion object {
        /**
         * Creates a new Result with the Outcome OK.
         * @return
         */
        fun getInstanceOk(): MessageResult = MessageResult(Outcome.OK, "")

        /**
         * Creates a new Result with the Outcome FAIL.
         * @return
         */
        fun getInstanceFail(): MessageResult = MessageResult(Outcome.FAIL, "")
    }

}