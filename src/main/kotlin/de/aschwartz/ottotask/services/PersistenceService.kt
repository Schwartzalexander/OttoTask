package de.aschwartz.ottotask.services

import de.aschwartz.ottotask.domain.Item
import de.aschwartz.ottotask.domain.ItemPackingUnitPriceHolder
import de.aschwartz.ottotask.domain.PackingUnit
import de.aschwartz.ottotask.domain.Price
import org.springframework.stereotype.Service

@Service
class PersistenceService {

    private val pricesByArticleId = mutableMapOf<String, Price>()
    private val itemsByArticleId = mutableMapOf<String, Item>()
    private val packingUnitsById = mutableMapOf<String, PackingUnit>()

    fun writePrice(price: Price) {
        pricesByArticleId[price.articleId] = price
    }

    fun writePackingUnit(packingUnit: PackingUnit) {
        packingUnitsById[packingUnit.packingUnitId] = packingUnit
    }

    fun writeItem(item: Item) {
        itemsByArticleId[item.articleId] = item
    }

    private fun findPriceByArticleId(articleId: String): Price? = pricesByArticleId[articleId]

    private fun findItem(articleId: String): Item? = itemsByArticleId[articleId]

    private fun findPackingUnit(packingUnitId: String): PackingUnit? = packingUnitsById[packingUnitId]

    /**
     * Finds all packing units for the given packingUnitId strings. If any is missing, null is returns.
     */
    private fun findPackingUnits(packingUnitIds: List<String>): List<PackingUnit>? {
        val packingUnits = mutableListOf<PackingUnit>()
        packingUnitIds.forEach {
            val packingUnit = findPackingUnit(it) ?: return null
            packingUnits.add(packingUnit)
        }
        return packingUnits
    }

    private fun findItemByPackingUnitId(packingUnitId: String): Item? {
        return itemsByArticleId.values.find { it.packingUnitIds.contains(packingUnitId) }
    }

    fun findDataForPrice(price: Price): ItemPackingUnitPriceHolder? {
        val articleId = price.articleId
        val item: Item = findItem(articleId) ?: return null
        val packingUnits: List<PackingUnit> = findPackingUnits(item.packingUnitIds) ?: return null
        return ItemPackingUnitPriceHolder(price, packingUnits, item)
    }


    fun findDataForPackingUnit(packingUnit: PackingUnit): ItemPackingUnitPriceHolder? {
        val item: Item = findItemByPackingUnitId(packingUnit.packingUnitId) ?: return null
        val articleId = item.articleId
        val price: Price = findPriceByArticleId(articleId) ?: return null
        val packingUnits: List<PackingUnit> = findPackingUnits(item.packingUnitIds) ?: return null
        return ItemPackingUnitPriceHolder(price, packingUnits, item)
    }


    fun findDataForItem(item: Item): ItemPackingUnitPriceHolder? {
        val articleId = item.articleId
        val price: Price = findPriceByArticleId(articleId) ?: return null
        val packingUnits: List<PackingUnit> = findPackingUnits(item.packingUnitIds) ?: return null
        return ItemPackingUnitPriceHolder(price, packingUnits, item)
    }

    fun deleteAll(itemPackingUnitPriceHolder: ItemPackingUnitPriceHolder) {
        deletePrice(itemPackingUnitPriceHolder.price.articleId)
        deleteItem(itemPackingUnitPriceHolder.item.articleId)
        deletePackingUnits(itemPackingUnitPriceHolder.packingUnits)
    }


    private fun deletePrice(articleId: String) {
        pricesByArticleId.remove(articleId)
    }

    private fun deleteItem(articleId: String) {
        itemsByArticleId.remove(articleId)
    }

    private fun deletePackingUnits(packingUnits: List<PackingUnit>) {
        packingUnits.forEach { deletePackingUnit(it.packingUnitId) }
    }

    private fun deletePackingUnit(packingUnitId: String) {
        packingUnitsById.remove(packingUnitId)
    }
}