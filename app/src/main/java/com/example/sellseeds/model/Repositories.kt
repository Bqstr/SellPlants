package com.example.sellseeds.model

import android.content.Context
import androidx.room.Room
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserCurrentIdRealization
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.shop.ShopRepositoryRealization
import com.example.sellseeds.model.shop.entities.ShopIdRealization
import com.example.sellseeds.model.user.RoomUserRepository
import com.example.sellseeds.model.user.UserRepository

object Repositories {
    lateinit var applicationContext : Context




    private val database1: AppDatabase by lazy<AppDatabase> {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
      //      .createFromAsset("initial_database.db")
            .build()
    }

    val userCurrentId: UserCurrentId by lazy{
        UserCurrentIdRealization(applicationContext)
    }
    val shopCurrentId:ShopCurrentId by lazy{
            ShopIdRealization(applicationContext)
    }


    val accountsRepository: UserRepository by lazy {
        RoomUserRepository(database1.getUserDao(), userCurrentId)
    }
    val shopRepository:ShopRepository by lazy{
        ShopRepositoryRealization(database1.getShopDao() , shopCurrentId = shopCurrentId)
    }



    fun init(context:Context){
        applicationContext =context
    }
}