package com.example.sellseeds.model.shop

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.entities.UserDbEntity

@Dao
interface ShopDao {
    @Insert
    fun registerShop(shopDbEntity: ShopDbEntity)

    @Query("SELECT * FROM shop where email =:email ")
    fun getByEmail(email:String):ShopDbEntity?

    @Update(entity = ShopDbEntity::class)
    suspend fun updateShopData(shop: Shop)
    @Query("Select * from shop where id=:id ")
    fun getById(id:Int): ShopDbEntity?

    @Query("Select * from shop ")
    fun getAll(): List<ShopDbEntity?>

}