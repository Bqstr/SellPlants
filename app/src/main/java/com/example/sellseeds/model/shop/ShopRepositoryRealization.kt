package com.example.sellseeds.model.shop

import android.util.Log
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity.Companion.toShop

class ShopRepositoryRealization(val dao: ShopDao , val shopCurrentId:ShopCurrentId//to do !!!!!!!!!!!!!!!!!!!!!
):ShopRepository {
    override suspend fun createShop(shop: Shop) {
        dao.registerShop(ShopDbEntity.fromShop(shop))
       shopCurrentId.setCurrentId(dao.getByEmail(shop.email)!!.id)
    }

    override suspend fun signInShop(email: String, password: String) :Boolean{
        val shop =dao.getByEmail(email) ?: return false

            if(shop.password ==password){


                shopCurrentId.setCurrentId(shop.id)

                return true

            }
            else{
                //Wrong password
                //shopCurrentId.setCurrentId(-1)
            }


        return false

    }

    override suspend fun  getShopById(id: Int): Shop? {
        if(dao.getById(id)==null){
            return null
        }
        else{
            return toShop(dao.getById(id)!!)

        }
    }

    override suspend fun updateShop(shop: Shop) {
        dao.updateShopData(shop)

    }

    override suspend fun getAllShops(): List<Shop> {
        if(dao.getAll()==null){
            return listOf()
        }
        val l =dao.getAll()!!
        for(a in 0 until l.size){
            Log.d("asdasdasdasd11","${l[a]!!.id}   ${l[a]!!.name}   ${l[a]!!.password}")

        }
        return dao.getAll()!!.map { shopDbEntity -> shopDbEntity!!.toShop() }
    }

    override suspend fun getShopsByName(): List<Shop> {
        val sh = dao.getShopsByName() ?: return listOf()

        return sh.map { ShopDbEntity -> ShopDbEntity.toShop() }


    }

    override suspend fun getShopsByName_decr(): List<Shop> {
        val sh = dao.getShopsByName_decr() ?: return listOf()

        return sh.map { ShopDbEntity -> ShopDbEntity.toShop() }

    }

}