package com.example.sellseeds.model.plants

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sellseeds.model.plants.entenies.PlantDbEntity

@Dao
interface PlantDao {
    @Query("Select * from plant")
    fun getAllPlants():List<PlantDbEntity?>?

    @Query("SELECT * FROM plant where id =:id")
    fun getPlantById(id:Int):PlantDbEntity?

    @Insert
    fun createPlant(plantDbEntity: PlantDbEntity)


    @Query("Select * from plant where shop_id =:shop_id")
    fun getPlantsByShopId(shop_id:Int):List<PlantDbEntity?>?






}