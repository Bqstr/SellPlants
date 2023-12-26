package com.example.sellseeds.model

import android.content.Context
import androidx.room.Room
import com.example.myapplication.UnsplashApi
import com.example.sellseeds.model.orders.OrdersRepository
import com.example.sellseeds.model.orders.OrdersRepositoryRealization
import com.example.sellseeds.model.plants.PlantRepositoryRealization
import com.example.sellseeds.model.plants.PlantsRepository
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserCurrentIdRealization
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.shop.ShopRepositoryRealization
import com.example.sellseeds.model.shop.entities.ShopIdRealization
import com.example.sellseeds.model.user.RoomUserRepository
import com.example.sellseeds.model.user.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repositories {
    lateinit var applicationContext : Context


    val retroFit =
        Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val unsplashApi =
        retroFit.create(UnsplashApi::class.java)



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
    val plantsRepository:PlantsRepository by lazy{
        PlantRepositoryRealization(database1.getPlantDao() , userCurrentId = userCurrentId, shopDao =  database1.getShopDao())
    }
    val ordersRepository:OrdersRepository by lazy{
        OrdersRepositoryRealization(database1.getOrdersDao() ,
            database1.getPlantDao() ,userCurrentId,shopCurrentId , database1.getUserDao() ,
            database1.getShopDao())
    }




    fun init(context:Context){
        applicationContext =context
    }
}