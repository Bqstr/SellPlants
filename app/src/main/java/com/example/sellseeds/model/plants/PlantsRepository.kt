package com.example.sellseeds.model.plants


import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.room.Query
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import kotlinx.coroutines.flow.Flow

interface PlantsRepository {

    suspend fun getPagedPlantsForShop(shopId:Int): Flow<PagingData<Seed>>

    suspend fun getShops():List<Shop>

    suspend fun addPlant(plant:Seed)

 //   fun deletePlant(plant:Plant)
   // suspend fun getPlantsByUserId(userId:Int)

    suspend fun getAllMyPlant(shopId:Int):List<Seed>

    suspend fun getAllPlants():List<Seed>?

    suspend fun getPlantById(id:Int): Seed?

    suspend fun getPlantsByShopId(shop_id:Int):List<Seed>?

    suspend  fun getPlantsByShopId_incr(shopId:Int):List<Seed>
    suspend  fun getPlantsByShopId_decr(shopId:Int):List<Seed>


    suspend fun getPlantByCategory_incr(userId: Int):List<Seed>


    suspend fun getPlantByCategory_decr(userId: Int):List<Seed>


    //suspend fun getPlantBySHopIdPaging(shopId :Int ,limit:Int ,offset:Int):List<Seed>
}