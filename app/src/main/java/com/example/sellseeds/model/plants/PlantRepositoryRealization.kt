package com.example.sellseeds.model.plants

import android.util.Log
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.plants.entenies.PlantDbEntity.Companion.toSeed
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.user.UserCurrentId

class PlantRepositoryRealization(val dao: PlantDao, val userCurrentId: UserCurrentId,val shopDao: ShopDao):PlantsRepository {
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


}