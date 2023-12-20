package com.example.sellseeds.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.entities.UserDbEntity

@Database(
    version = 1,
    entities = [UserDbEntity::class ,ShopDbEntity::class]
)
@TypeConverters(UserConverters::class )
abstract class AppDatabase:RoomDatabase() {

    abstract fun getUserDao():UserDao

    abstract fun getShopDao(): ShopDao


}