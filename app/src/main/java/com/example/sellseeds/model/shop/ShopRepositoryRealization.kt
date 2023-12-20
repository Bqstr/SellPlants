package com.example.sellseeds.model.shop

import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.fragments.Buyer.registerUser.UserCurrentId
import com.example.sellseeds.model.UserDao
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity.Companion.toShop

class ShopRepositoryRealization(val dao: ShopDao , val shopCurrentId:ShopCurrentId//to do !!!!!!!!!!!!!!!!!!!!!
):ShopRepository {
    override suspend fun createShop(shop: Shop) {
        dao.registerShop(ShopDbEntity.fromShop(shop))
        shopCurrentId.setCurrentId(shop.id)
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
        return dao.getAll().map { shopDbEntity -> shopDbEntity!!.toShop() }
    }

}