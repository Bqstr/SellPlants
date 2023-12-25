package com.example.sellseeds.model.plants.entenies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.entities.UserDbEntity
import java.util.PriorityQueue

@Entity(tableName = "plant",

    foreignKeys = [
        ForeignKey(
            entity = ShopDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["shop_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
    )
class PlantDbEntity(
    @PrimaryKey(autoGenerate = true) val  id:Long,
    @ColumnInfo(name ="name") val name:String,
    @ColumnInfo(name ="description") val description:String,
    @ColumnInfo(name ="price") val price:Int,
    @ColumnInfo(name ="images") val images :String,
    @ColumnInfo(name ="quantity") val quantity:Int,
    @ColumnInfo(name ="haveDiscount") val haveDiscount: Boolean,
    @ColumnInfo(name ="discount") val discount:Double?,
    @ColumnInfo(name ="category") val category: Category,
    @ColumnInfo(name ="shop_id") val shop_id: Int,

    ) {

    fun toSeed():Seed{
        var discount1 =Discount()
        if(haveDiscount){
            discount1 = Discount(true, discount)
        }
        return Seed(id ,name ,description ,price ,images ,category ,quantity, discount1 ,shop_id)

    }



    companion object{
        fun toSeed( plantDbEntity: PlantDbEntity):Seed{
            var discount =Discount()
            if(plantDbEntity.haveDiscount){
                discount = Discount(true, plantDbEntity.discount)
            }
            return Seed(plantDbEntity.id ,plantDbEntity.name ,plantDbEntity.description ,plantDbEntity.price ,plantDbEntity.images ,plantDbEntity.category ,plantDbEntity.quantity, discount ,plantDbEntity.shop_id)

        }
        fun fromSeed( seed: Seed): PlantDbEntity {

            if(seed.discount.haveDiscount){
                return PlantDbEntity(seed.id ,seed.name ,seed.description ,seed.price ,seed.images ,seed.quantity ,true ,seed.discount.amoutOfPrice ,seed.category, seed.shop_id)

            }
            return PlantDbEntity(seed.id ,seed.name ,seed.description ,seed.price ,seed.images ,seed.quantity ,false ,0.0 ,seed.category, seed.shop_id)

        }
    }

}