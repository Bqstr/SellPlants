package com.example.sellseeds.model.orders

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.entities.UserDbEntity

@Entity(tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = PlantDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["plant_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ])
class OrderDbEntity(
    @PrimaryKey(autoGenerate = true)var id:Int,
    @ColumnInfo(name ="price")var price:Int,
    @ColumnInfo(name ="amount")var amount: Int,
    @ColumnInfo(name ="plant_id")var plant_id:Int,
    @ColumnInfo(name ="user_id")var user_id:Int?,
    @ColumnInfo(name ="status")var status: OrderStatus,
    @ColumnInfo(name ="date")var date:Int,
    @ColumnInfo(name ="adress")var adress:String
) {

    fun toOrder(): Orders {
        return Orders(id, price ,null,amount ,plant_id,user_id,null, status ,date ,adress )
    }


companion object{
    fun fromOrder(order:Orders):OrderDbEntity{
        return OrderDbEntity(order.id ,order.price ,order.amount ,order.plant_id ,order.user_id ,order.status ,order.date ,order.adress)

    }
}
}
