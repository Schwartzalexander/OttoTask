package de.aschwartz.ottotask.domain

import de.aschwartz.ottotask.enums.ChangeType

/**
 * Represents article data sent to consumers by the application.
 * @property type the type of the article data (DELETED or CHANGED)
 * @property articleId the ID of the article
 * @property packingUnits a list of IDs of the packing units associated with the article (empty list for DELETED events)
 * @property heavyPackingUnits the number of packing units associated with the article that have a weight greater than 35 (0 for DELETED events)
 * @property sellingPrice the selling price associated with the article, or null for DELETED events
 */
data class ItemAggregate(
    val type: ChangeType,
    val articleId: String,
    val packingUnits: List<String>? = null,
    val heavyPackingUnits: Int? = null,
    val sellingPrice: Double? = null
)