package com.example.sellseeds.model.plants

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.plants.entenies.PlantDbEntity.Companion.toSeed
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.paging.PlantPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PlantRepositoryRealization(val dao: PlantDao, val userCurrentId: UserCurrentId,val shopDao: ShopDao,val ioDsipatcher: CoroutineContext):PlantsRepository {
    override suspend fun getPagedPlantsForShop(shopId: Int): Flow<PagingData<Seed>> {

        return Pager(config =PagingConfig(
            pageSize = PAGE_SIZE , enablePlaceholders = false
        ),
            pagingSourceFactory = {PlantPagingSource(plantDao = dao, shopId =shopId , pageSize = PAGE_SIZE)
            }
        ).flow




    }


    override suspend  fun getShops(): List<Shop> {
        if(shopDao.getAll()==null){
            return listOf()
        }
        return shopDao.getAll()!!.map { shopDbEntity -> shopDbEntity!!.toShop() }

    }

    override suspend  fun addPlant(seed: Seed) {
        dao.createPlant(PlantDbEntity.fromSeed(seed))
            }

    override suspend fun getAllMyPlant(shopId: Int): List<Seed> {
        val ssd =dao.getAllMyPlant(shopId) ?: listOf()
        return ssd.map { PlantDbEntity -> PlantDbEntity.toSeed() }
    }


    override suspend  fun getAllPlants(): List<Seed>{
        if(dao.getAllPlants()!=null){
            return dao.getAllPlants()!!.map { PlantDbEntity -> toSeed(PlantDbEntity!!) }

        }
        return listOf()
    }

    override suspend  fun getPlantById(id: Int): Seed? {

        return dao.getPlantById(id)!!.toSeed()
    }

    override suspend  fun getPlantsByShopId(shop_id: Int): List<Seed>? {
        val plants =dao.getPlantsByShopId(shop_id)
        Log.d("1231232ddddddr",plants.toString())
        Log.d("1231232ddddddr",shop_id.toString())

        if(plants==null){
            return listOf()
        }
        else{
            return plants.map { PlantDbEntity -> PlantDbEntity!!.toSeed() }
        }
    }

    override suspend fun getPlantsByShopId_incr(shopId: Int): List<Seed> {
        val qwe =dao.getPlantsByShopId_incr(shopId) ?: return listOf()
        return qwe.map { PlantDbEntity -> PlantDbEntity.toSeed() }
    }

    override suspend fun getPlantsByShopId_decr(shopId: Int): List<Seed> {
        val asd =dao.getPlantsByShopId_decr(shopId) ?: return listOf()
        return asd.map { PlantDbEntity -> PlantDbEntity.toSeed() }

    }

    override suspend fun getPlantByCategory_incr(shopId: Int): List<Seed> {
        val sssdw =dao.getPlantByCategory_incr(shopId) ?: return listOf()
        return sssdw.map { PlantDbEntity -> PlantDbEntity.toSeed() }

    }

    override suspend fun getPlantByCategory_decr(shopId: Int): List<Seed> {
        val sssdw =dao.getPlantByCategory_decr(shopId) ?: return listOf()
        return sssdw.map { PlantDbEntity -> PlantDbEntity.toSeed() }
    }

//    override suspend fun getPlantBySHopIdPaging(shopId: Int, limit: Int, offset: Int): List<Seed> {
//        val s =dao.getPlantByShopIdPaging(shopId ,limit ,offset) ?: return listOf()
//        return s.map { PlantDbEntity -> PlantDbEntity.toSeed() }
//    }
    companion object{
        val PAGE_SIZE =20
    }




}