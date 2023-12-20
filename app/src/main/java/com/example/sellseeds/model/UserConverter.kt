package com.example.sellseeds.model

import androidx.room.TypeConverter
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.entities.UserDbEntity

class UserConverters {
        @TypeConverter
        fun userFromDb(userDbEntity: UserDbEntity): User {
            return User(
                id = userDbEntity.id,
                email = userDbEntity.email,
                name = userDbEntity.name,
                adress = userDbEntity.adress,
                number = userDbEntity.number,
                password = userDbEntity.password,
                orders = mutableListOf()
            )
        }

        @TypeConverter
        fun userToDb(signUpData: User): UserDbEntity {
            return UserDbEntity(
                id = 0,
                email = signUpData.email,
                name = signUpData.name,
                adress = signUpData.adress,
                number = signUpData.number,
                password = signUpData.password
            )

        }

    @TypeConverter
    fun fromShop(shop: Shop): ShopDbEntity {
        return ShopDbEntity(shop.id ,email =shop.email ,name =shop.name,adress =  shop.adress ,password =shop.password ,number =shop.number  ,fee =shop.fee, image =shop.image)

    }
    @TypeConverter
    fun toShop(shopDbEntity: ShopDbEntity): Shop {
        return Shop(shopDbEntity.id ,name = shopDbEntity.name ,email  =shopDbEntity.email ,adress =shopDbEntity.adress ,number =shopDbEntity.number, products = mutableListOf() ,
            mutableListOf() ,image =shopDbEntity.image , rating = Rating.FOUR_STAR , fee = shopDbEntity.fee , password =  shopDbEntity.password)
    }
    }
