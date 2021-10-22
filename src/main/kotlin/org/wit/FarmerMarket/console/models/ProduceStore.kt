package org.wit.FarmerMarket.console.models

interface ProduceStore {
    fun findAll(): List<FarmerMarketModel>
    fun findOne(id: Long): FarmerMarketModel?
    fun create(Produce: FarmerMarketModel)
    fun update(Produce: FarmerMarketModel)
    fun delete(Produce: FarmerMarketModel)
}