package org.wit.FarmerMarket.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.FarmerMarket.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "FarmerMarket.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FarmerMarketModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ProduceJSONStore : ProduceStore {

    var Produces = mutableListOf<FarmerMarketModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FarmerMarketModel> {
        return Produces
    }

    override fun findOne(id: Long) : FarmerMarketModel? {
        var foundProduce: FarmerMarketModel? = Produces.find { p -> p.id == id }
        return foundProduce
    }

    override fun create(Produce: FarmerMarketModel) {
        Produce.id = generateRandomId()
        Produces.add(Produce)
        serialize()
    }
    internal fun getId(): Long {
        return lastId++
    }

    override fun update(Produce: FarmerMarketModel) {
        var foundProduce = findOne(Produce.id!!)
        if (foundProduce != null) {
            foundProduce.title = Produce.title
            foundProduce.description = Produce.description
        }
        serialize()
    }

    override fun delete(Produce: FarmerMarketModel) {
        Produces.remove(Produce)
        serialize()
    }

    internal fun logAll() {
        Produces.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(Produces, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        Produces = Gson().fromJson(jsonString, listType)
    }
}