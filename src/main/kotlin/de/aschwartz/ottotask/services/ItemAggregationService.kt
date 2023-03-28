package de.aschwartz.ottotask.services

import de.aschwartz.ottotask.domain.*
import de.aschwartz.ottotask.enums.ChangeType
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

@Service
class ItemAggregationService(
    val persistenceService: PersistenceService,
    val heavyPackingUnitService: HeavyPackingUnitService
) {

    private val logger: Log = LogFactory.getLog(this.javaClass)

    fun onPriceReceived(price: Price) {
        persistenceService.writePrice(price)
        val itemPackingUnitPriceHolder: ItemPackingUnitPriceHolder = persistenceService.findDataForPrice(price) ?: return
        handleCompleteDataSet(itemPackingUnitPriceHolder)
    }

    fun onPackingUnitReceived(packingUnit: PackingUnit) {
        persistenceService.writePackingUnit(packingUnit)
        val itemPackingUnitPriceHolder: ItemPackingUnitPriceHolder = persistenceService.findDataForPackingUnit(packingUnit) ?: return
        handleCompleteDataSet(itemPackingUnitPriceHolder)

    }

    fun onItemReceived(item: Item) {
        persistenceService.writeItem(item)
        val itemPackingUnitPriceHolder: ItemPackingUnitPriceHolder = persistenceService.findDataForItem(item) ?: return
        handleCompleteDataSet(itemPackingUnitPriceHolder)

    }


    private fun handleCompleteDataSet(itemPackingUnitPriceHolder: ItemPackingUnitPriceHolder): ItemAggregate {
        persistenceService.deleteAll(itemPackingUnitPriceHolder)
        val itemAggregate: ItemAggregate = convertToItemAggregate(itemPackingUnitPriceHolder)
        logger.info("Output value ready: $itemAggregate")
        return itemAggregate
    }

    private fun convertToItemAggregate(holder: ItemPackingUnitPriceHolder): ItemAggregate {
        return when (val changeType = holder.item.type) {
            ChangeType.CHANGED -> {
                val packingUnitIds = holder.packingUnits.map { it.packingUnitId }
                val heavyPackingUnits = heavyPackingUnitService.countHeavyPackingUnits(holder)
                ItemAggregate(changeType, holder.item.articleId, packingUnitIds, heavyPackingUnits, holder.price.sellingPrice)
            }

            ChangeType.DELETED -> {
                ItemAggregate(changeType, holder.item.articleId)
            }
        }
    }

}