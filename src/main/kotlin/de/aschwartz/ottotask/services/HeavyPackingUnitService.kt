package de.aschwartz.ottotask.services

import de.aschwartz.ottotask.domain.ItemPackingUnitPriceHolder
import de.aschwartz.ottotask.util.Constants
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class HeavyPackingUnitService(
    environment: Environment,
) {

    val heavyPackingUnitWeightThreshold =
        environment.getProperty(Constants.PROPERTY_HEAVY_PACKING_UNIT_WEIGHT_THRESHOLD)?.toInt() ?: 35

    fun countHeavyPackingUnits(itemPackingUnitPriceHolder: ItemPackingUnitPriceHolder) =
        itemPackingUnitPriceHolder.packingUnits.count { it.weight > heavyPackingUnitWeightThreshold }
}