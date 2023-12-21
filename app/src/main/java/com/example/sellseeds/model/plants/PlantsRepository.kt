package com.example.sellseeds.model.plants


import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.entenies.PlantDbEntity

interface PlantsRepository {

    suspend fun getShops():List<Shop>

    suspend fun addPlant(plant:Seed)

 //   fun deletePlant(plant:Plant)


    suspend fun getAllPlants():List<Seed?>?

    suspend fun getPlantById(id:Int): Seed?

    suspend fun getPlantsByShopId(shop_id:Int):List<Seed>?
}