package org.wit.FarmerMarket.console.views

import org.wit.FarmerMarket.console.main.FarmerMarketView
import org.wit.FarmerMarket.console.main.Produces
import org.wit.FarmerMarket.console.models.ProduceMemStore
import org.wit.FarmerMarket.console.models.ProduceJSONStore
import org.wit.FarmerMarket.console.models.FarmerMarketModel

class FarmerMarketView {
    fun menu() : Int {

        var option : Int
        var input: String?
        // I referenced this code to add colour (https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println)
        val red = "\u001b[31m"
        val green = "\u001B[32m"
        val yellow = "\u001B[33m"
        val blue = "\u001B[34m"
        val purple = "\u001B[35m"
        val cyan = "\u001B[36m"
        val reset = "\u001b[0m"
        println(red + "MAIN MENU"+ reset)
        println(green + " 1. Add Produce" + reset)
        println( yellow+" 2. Update Produce" + reset)
        println(blue +" 3. List All Produce" + reset)
        println(purple +" 4. Search Produce" + reset)
        println(cyan +" 5. Delete Produce" + reset)
        println("-1. Exit")
        println()
        print( red + "Enter Option : " + reset)
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listProduces(Produces : ProduceJSONStore) {
        println("List All Produces")
        println()
        Produces.logAll()
        println()
    }

    fun showProduce(Produce : FarmerMarketModel) {
        if(Produce != null)
            println("Produce Details [ $Produce ]")
        else
            println("Produce Not Found...")
    }

    fun addProduceData(Produce : FarmerMarketModel) : Boolean {

        println()
        print("Enter a Title : ")
        Produce.title = readLine()!!
        print("Enter a Description : ")
        Produce.description = readLine()!!

        return Produce.title.isNotEmpty() && Produce.description.isNotEmpty()
    }

    fun updateProduceData(Produce : FarmerMarketModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (Produce != null) {
            print("Enter a new Title for [ " + Produce.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + Produce.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                Produce.title = tempTitle
                Produce.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}