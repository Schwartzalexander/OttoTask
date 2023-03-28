package de.aschwartz.ottotask.domain

/**
 * Represents packing unit data obtained via an HTTP feed (REST endpoint).
 * @property packingUnitId the ID of the packing unit
 * @property width the width of the package
 * @property height the height of the package
 * @property length the length of the package
 * @property weight the weight of the package
 */
data class PackingUnit(
    val packingUnitId: String,
    val width: Double,
    val height: Double,
    val length: Double,
    val weight: Double
)