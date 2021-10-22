package org.wit.FarmerMarket.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ProduceMemStore : ProduceStore {
    val Produces = ArrayList<FarmerMarketModel>()

    override fun findAll(): List<FarmerMarketModel> {
        return Produces
    }

    override fun findOne(id: Long) : FarmerMarketModel? {
        var foundProduce: FarmerMarketModel? = Produces.find { p -> p.id == id }
        return foundProduce
    }

    override fun create(Produce: FarmerMarketModel) {
        Produce.id = getId()
        Produces.add(Produce)
        logAll()
    }

    override fun update(Produce: FarmerMarketModel) {
        var foundProduce = findOne(Produce.id!!)
        if (foundProduce != null) {
            foundProduce.title = Produce.title
            foundProduce.description = Produce.description
        }
    }
    override fun delete(Produce: FarmerMarketModel) {
        Produces.remove(Produce)
    }

    internal fun logAll() {
        Produces.forEach { logger.info("${it}") }
    }
}