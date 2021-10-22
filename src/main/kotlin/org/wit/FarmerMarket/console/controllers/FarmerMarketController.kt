package org.wit.FarmerMarket.console.controllers

import mu.KotlinLogging
import org.wit.FarmerMarket.console.models.ProduceMemStore
import org.wit.FarmerMarket.console.models.FarmerMarketModel
import org.wit.FarmerMarket.console.models.ProduceJSONStore
import org.wit.FarmerMarket.console.views.FarmerMarketView

class FarmerMarketController {
    //val Produces = ProduceMemStore()
    val FarmerMarketView = FarmerMarketView()
    val Produces = ProduceJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Farmers Market Console App" }
        println("Farmers Market App Version 1.0")
    }
    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Farmer Market Console App" }
    }
    fun menu() :Int { return FarmerMarketView.menu() }

    fun add(){
        var aProduce = FarmerMarketModel()

        if (FarmerMarketView.addProduceData(aProduce))
            Produces.create(aProduce)
        else
            logger.info("Produce Not Added")
    }

    fun list() {
        FarmerMarketView.listProduces(Produces)
    }

    fun update() {

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

    fun delete() {
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

    fun search() {
        val aProduce = search(FarmerMarketView.getId())!!
        FarmerMarketView.showProduce(aProduce)
    }


    fun search(id: Long) : FarmerMarketModel? {
        var foundProduce = Produces.findOne(id)
        return foundProduce
    }

}