package de.aschwartz.ottotask.util

import java.util.*

/**
 * A simple name generator, which puts together three syllables.
 */
object RandomNameGenerator {
    private val BEGINNING = arrayOf(
        "A", "Vin", "Ju", "A", "Ste", "Jü", "Pau", "He", "Lau", "Lo", "Pe", "Pa", "Pi", "Mon", "E", "Ro", "Ki",
        "Mu", "Lin", "Li", "Fran", "Pi", "An", "E", "Ma"
    )
    private val MIDDLE = arrayOf(
        "lex", "cent", "lia", "ma", "fan", "rgen", "li", "le", "ra", "li", "ter", "na", "ka", "san", "lisa", "sa", "a",
        "da", "ba", "zis", "mu", "ge", "der", "ria", "mi"
    )
    private val END = arrayOf(
        "ander", "nda", "ne", "na", "ta", "ma", "chu", "to", "beth", "li", "ra", "non", "ka", "la", "lika", "", "", "",
        "", "", "", "", "", "", ""
    )
    private val rand = Random()

    /**
     * Generates and returns a simple name.
     */
    fun generateName(): String {
        return BEGINNING[rand.nextInt(BEGINNING.size)] + MIDDLE[rand.nextInt(MIDDLE.size)] + END[rand.nextInt(END.size)]
    }
}