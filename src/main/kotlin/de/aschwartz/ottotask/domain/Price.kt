package de.aschwartz.ottotask.domain

import java.util.*

/**
 * Represents a price of an article.
 * @property articleId the ID of the article
 * @property sellingPrice the selling price of the article
 * @property validFrom the date and time from which this price is valid
 */
data class Price(
    val articleId: String,
    val sellingPrice: Double,
    val validFrom: Date
)
