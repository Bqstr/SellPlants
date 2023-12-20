package com.example.sellseeds.model.plants


import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.entenies.PlantDbEntity

interface PlantsRepository {

    fun getShops():List<Shop>

    fun addPlant(plant:Seed)

 //   fun deletePlant(plant:Plant)


    fun getAllPlants():List<Seed?>?

    fun getPlantById(id:Int): Seed?

    fun getPlantsByShopId(shop_id:Int):List<PlantDbEntity?>?
}