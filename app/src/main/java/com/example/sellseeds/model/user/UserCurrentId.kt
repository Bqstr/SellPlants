package com.example.sellseeds.model.user

interface UserCurrentId {
    suspend fun getCurrentUserId(): Int


    suspend fun setCurrentUserId(accountId: Int)
}