package de.aschwartz.ottotask.domain

import de.aschwartz.ottotask.enums.ChangeType

/**
 * Represents article data obtained via a Kafka topic.
 * @property type the type of the article data (CHANGE or DELETED)
 * @property articleId the ID of the article
 * @property packingUnitIds a list of IDs of the packing units associated with the article
 */
data class Item(
    val type: ChangeType,
    val articleId: String,
    val packingUnitIds: List<String>
)