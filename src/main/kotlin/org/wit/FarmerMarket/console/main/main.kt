package org.wit.FarmerMarket.console.main

import mu.KotlinLogging
import org.wit.FarmerMarket.console.controllers.FarmerMarketController
import org.wit.FarmerMarket.console.models.FarmerMarketModel
import org.wit.FarmerMarket.console.models.ProduceJSONStore
import org.wit.FarmerMarket.console.models.ProduceMemStore
import org.wit.FarmerMarket.console.views.FarmerMarketView

private val logger = KotlinLogging.logger {}


//val Produces = ProduceMemStore()
val FarmerMarketView = FarmerMarketView()
val controller = FarmerMarketController()
val Produces = ProduceJSONStore()

fun main(args: Array<String>) {
    logger.info { "Launching Farmers Market Console App" }
    println("Farmers Market Kotlin App Version 1.0")

    var input: Int

    do {
        input = FarmerMarketView.menu()
        when(input) {
            1 -> addProduce()
            2 -> updateProduce()
            3 -> FarmerMarketView.listProduces(Produces)
            4 -> searchProduce()
            5 -> deleteProduce()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Farmers Market Console App" }
}


fun addProduce(){
    var aProduce = FarmerMarketModel()

    if (FarmerMarketView.addProduceData(aProduce))
        Produces.create(aProduce)
    else
        logger.info("Produce Not Added")
}
fun updateProduce() {
    FarmerMarketView.listProduces(Produces)
    var searchId = FarmerMarketView.getId()
    val aProduce = search(searchId)

    if(aProduce != null) {
        if(FarmerMarketView.updateProduceData(aProduce)) {
            Produces.update(aProduce)
            FarmerMarketView.showProduce(aProduce)
            logger.info("Produce Updated : [ $aProduce ]")
        }
        else
            logger.info("Produce Not Updated")
    }
    else
        println("Produce Not Updated...")
}
fun deleteProduce() {
    FarmerMarketView.listProduces(Produces)
    var searchId = FarmerMarketView.getId()
    val aProduce = search(searchId)

    if(aProduce != null) {
        Produces.delete(aProduce)
        println("Produce Deleted...")
        FarmerMarketView.listProduces(Produces)
    }
    else
        println("Placemark Not Deleted...")
}


fun searchProduce() {

    val aProduce = search(FarmerMarketView.getId())!!
    FarmerMarketView.showProduce(aProduce)
}


fun search(id: Long) : FarmerMarketModel? {
    var foundProduce: FarmerMarketModel? = Produces.findOne(id)
    return foundProduce
}
