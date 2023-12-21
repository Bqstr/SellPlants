package com.example.sellseeds.model

import androidx.room.Database
import androidx.room.Index
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sellseeds.model.orders.OrderDbEntity
import com.example.sellseeds.model.orders.OrdersDao
import com.example.sellseeds.model.plants.PlantDao
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.UserDao
import com.example.sellseeds.model.user.entities.UserDbEntity

@Database(
    version = 1,
    entities = [UserDbEntity::class ,ShopDbEntity::class ,PlantDbEntity::class ,OrderDbEntity::class]
)
@TypeConverters(UserConverters::class )
abstract class AppDatabase:RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getShopDao(): ShopDao

    abstract fun getPlantDao():PlantDao

    abstract fun getOrdersDao():OrdersDao




}