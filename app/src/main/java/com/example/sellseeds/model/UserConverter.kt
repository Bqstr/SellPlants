package com.example.sellseeds.model

import androidx.room.TypeConverter
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
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
@TypeConverter
    fun toSeed(plantDbEntity:PlantDbEntity): Seed {
        if(plantDbEntity.haveDiscount){
            return Seed(plantDbEntity.id, plantDbEntity.name ,plantDbEntity.description ,plantDbEntity.price,plantDbEntity.images,plantDbEntity.category ,plantDbEntity.quantity,
                Discount(true ,plantDbEntity.discount), plantDbEntity.shop_id
            )

        }
        else{
            return Seed(plantDbEntity.id, plantDbEntity.name ,plantDbEntity.description ,plantDbEntity.price,plantDbEntity.images,plantDbEntity.category ,plantDbEntity.quantity,
                Discount(), plantDbEntity.shop_id
            )
        }
    }
@TypeConverter
        fun fromSeed( seed: Seed): PlantDbEntity {

            if(seed.discount.haveDiscount){
                return PlantDbEntity(seed.id ,seed.name ,seed.description ,seed.price ,seed.images ,seed.quantity ,true ,seed.discount.amoutOfPrice ,seed.category, seed.shop_id)

            }
            return PlantDbEntity(seed.id ,seed.name ,seed.description ,seed.price ,seed.images ,seed.quantity ,false ,0.0 ,seed.category, seed.shop_id)

        }


    }
