package com.example.sellseeds.model.user

import android.content.Context

class UserCurrentIdRealization(appContext: Context): UserCurrentId {
    private val sharedPreferences = appContext.getSharedPreferences("current_id", Context.MODE_PRIVATE////access only from this app
    )


    override suspend fun getCurrentUserId() :Int =sharedPreferences.getInt(CURRENT_USER_ID ,-1)




    override suspend fun setCurrentUserId(accountId: Int){
        sharedPreferences.edit()
            .putInt(CURRENT_USER_ID, accountId)
            .apply()
    }
    companion object {
        private const val CURRENT_USER_ID = "CURRENT_USER_ID"
    }
}