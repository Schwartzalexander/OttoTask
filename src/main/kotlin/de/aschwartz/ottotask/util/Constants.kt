package de.aschwartz.ottotask.util

/**
 *
 * <P>
 * A class, that holds global constants
</P> * <P></P>
 *
 * @author Alexander Schwartz Created 08.11.2021
 */
object Constants {
    /**
     * The path of the config dir relative to the project root. E.g. "", "config/" or "C:/"
     */
    const val GLOBAL_CONFIG_DIR = ""
    const val TIME_FORMAT = "%dd %02d:%02d:%02d"
    const val TIME_FORMAT_SHORT = "%02d:%02d:%02d"
    const val DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss"

    const val PROPERTY_HEAVY_PACKING_UNIT_WEIGHT_THRESHOLD = "heavy-packing-unit.weight.threshold"
}