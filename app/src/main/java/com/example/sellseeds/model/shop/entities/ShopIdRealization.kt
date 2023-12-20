package com.example.sellseeds.model.shop.entities

import android.content.Context
import com.example.sellseeds.model.shop.ShopCurrentId

class ShopIdRealization(appContext:Context):ShopCurrentId {
    private val sharedPreferences = appContext.getSharedPreferences("shop_current_id", Context.MODE_PRIVATE////access only from this app
    )


    override fun getCurrentId(): Int =sharedPreferences.getInt(CURRENT_SHOP_ID ,-1)




    override fun setCurrentId(shopId: Int){
        sharedPreferences.edit()
            .putInt(CURRENT_SHOP_ID, shopId)
            .apply()
    }
    companion object {
        private const val CURRENT_SHOP_ID = "CURRENT_SHOP_ID"
    }
}