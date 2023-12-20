package com.example.sellseeds.fragments.Seller.registerSeller

import android.content.Context

class SellerCurrentIdRealization(appContext:Context):SellerCurrentId {
    private val sharedPreferences = appContext.getSharedPreferences("current_id", Context.MODE_PRIVATE////access only from this app
    )


    override fun getCurrentSellerId() :Int =sharedPreferences.getInt(CURRENT_SELLER_ID ,-1)




    override fun setCurrentSellerId(accountId: Int){
        sharedPreferences.edit()
            .putInt(CURRENT_SELLER_ID, accountId)
            .apply()
    }
    companion object {
        private const val CURRENT_SELLER_ID = "CURRENT_USER_ID"
    }
}