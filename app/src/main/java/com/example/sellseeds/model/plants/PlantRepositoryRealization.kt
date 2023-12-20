package com.example.sellseeds.model.plants

import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.plants.entenies.PlantDbEntity.Companion.toSeed
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.user.UserCurrentId

class PlantRepositoryRealization(val dao: PlantDao, val userCurrentId: UserCurrentId,val shopDao: ShopDao):PlantsRepository {
    override fun getShops(): List<Shop> {
        if(shopDao.getAll()==null){
            return listOf()
        }
        return shopDao.getAll()!!.map { shopDbEntity -> shopDbEntity!!.toShop() }

    }

    override fun addPlant(seed: Seed) {
        dao.createPlant(PlantDbEntity.fromSeed(seed))
            }




    override fun getAllPlants(): List<Seed>{
        if(dao.getAllPlants()!=null){
            return dao.getAllPlants()!!.map { PlantDbEntity -> toSeed(PlantDbEntity!!) }

        }
        return listOf()
    }

    override fun getPlantById(id: Int): Seed? {

        return dao.getPlantById(id)!!.toSeed()
    }

    override fun getPlantsByShopId(shop_id: Int): List<PlantDbEntity?>? {
        if(dao.getPlantsByShopId(shop_id)==null){
            return listOf()
        }
        else{
            return dao.getPlantsByShopId(shop_id)
        }
    }




}