package com.example.sellseeds.model.plants

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sellseeds.model.orders.OrderDbEntity
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity

@Dao
interface PlantDao {
    @Query("Select * from plant")
    suspend fun getAllPlants():List<PlantDbEntity?>?

    @Query("SELECT * FROM plant where id =:id")
    suspend fun getPlantById(id:Int):PlantDbEntity?

    @Query("SELECT * FROM plant WHERE shop_id = :shopId")
    suspend fun getAllMyPlant(shopId:Int):List<PlantDbEntity>?

    @Insert
    suspend  fun createPlant(plantDbEntity: PlantDbEntity)


    @Query("Select * from plant where shop_id =:shop_id")
    suspend fun getPlantsByShopId(shop_id:Int):List<PlantDbEntity?>?


    @Query("SELECT plant.* FROM plant WHERE shop_id = :shopId ORDER BY name ASC ")
    suspend  fun getPlantsByShopId_incr(shopId:Int):List<PlantDbEntity>?
    @Query("SELECT plant.* FROM plant WHERE shop_id = :shopId ORDER BY name DESC ")
    suspend  fun getPlantsByShopId_decr(shopId:Int):List<PlantDbEntity>?




    @Query("SELECT * FROM plant WHERE shop_id = :shopId ORDER BY CASE WHEN plant.category like 'Bi%' THEN 1 WHEN plant.category like 'M%' THEN 2 WHEN plant.category like 'Sm%' THEN 3 END")
    suspend fun getPlantByCategory_incr(shopId: Int):List<PlantDbEntity>?

    @Query("SELECT * FROM plant WHERE shop_id = :shopId ORDER BY CASE WHEN plant.category like 'Sm%' THEN 1 WHEN plant.category like 'M%' THEN 2 WHEN plant.category like 'Bi%' THEN 3 end")
    suspend fun getPlantByCategory_decr(shopId: Int):List<PlantDbEntity>?




}