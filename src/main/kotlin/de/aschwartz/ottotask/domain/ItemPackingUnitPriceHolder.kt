package de.aschwartz.ottotask.domain

data class ItemPackingUnitPriceHolder(
    val price: Price,
    val packingUnits: List<PackingUnit>,
    val item: Item
)
