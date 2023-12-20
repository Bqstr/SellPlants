package com.example.sellseeds.model.shop

import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.shop.entities.ShopDbEntity

interface ShopRepository {
    suspend fun createShop(shop: Shop)

    suspend fun signInShop(email:String, password:String):Boolean

    suspend    fun getShopById(id:Int):Shop?

    suspend    fun updateShop(shop: Shop)

    suspend    fun getAllShops():List<Shop>
}