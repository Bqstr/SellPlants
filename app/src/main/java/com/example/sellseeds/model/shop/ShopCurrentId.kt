package com.example.sellseeds.model.shop

interface ShopCurrentId {
    suspend fun getCurrentId():Int

    suspend fun setCurrentId(id:Int)
}