package com.example.sellseeds.model.shop.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.shop.ShopDao

@Entity(tableName = "shop")
class ShopDbEntity(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name ="email") val email:String,
    @ColumnInfo(name ="name") val name:String,
    @ColumnInfo(name ="adress")  val adress:String,
    @ColumnInfo(name ="password") val password:String,
    @ColumnInfo(name ="number") val number:String,
    @ColumnInfo(name ="image") val image:Int,
    @ColumnInfo(name ="fee") val fee:Int ) {

    fun toShop():Shop{
        return Shop(id ,name =name ,email  =email ,adress =adress ,number =number, products = mutableListOf() ,
            mutableListOf() ,image =image , rating = Rating.FOUR_STAR , fee = fee , password =  password)
    }
    companion object{
        fun fromShop(shop: Shop):ShopDbEntity{
            return ShopDbEntity(shop.id ,email =shop.email ,name =shop.name,adress =  shop.adress ,password =shop.password ,number =shop.number  ,fee =shop.fee, image =shop.image)

        }

        fun toShop(shopDbEntity:ShopDbEntity):Shop{
            return Shop(shopDbEntity.id ,name =shopDbEntity.name ,email  =shopDbEntity.email ,adress =shopDbEntity.adress ,number =shopDbEntity.number, products = mutableListOf() ,
                mutableListOf() ,image =shopDbEntity.image , rating = Rating.FOUR_STAR , fee = shopDbEntity.fee , password =  shopDbEntity.password)
        }



    }

}