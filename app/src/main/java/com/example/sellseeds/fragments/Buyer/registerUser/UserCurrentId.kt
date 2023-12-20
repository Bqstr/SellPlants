package com.example.sellseeds.fragments.Buyer.registerUser

interface UserCurrentId {
    suspend fun getCurrentUserId(): Int


    suspend fun setCurrentUserId(accountId: Int)
}