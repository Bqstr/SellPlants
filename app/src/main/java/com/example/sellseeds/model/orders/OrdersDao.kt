package com.example.sellseeds.model.orders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sellseeds.model.shop.entities.ShopDbEntity

@Dao
interface OrdersDao {
    @Query("Select * from orders")
    fun getAllOrders():List<OrderDbEntity>?

    @Query(" Select * from orders where id =:id" )
    fun getOrderById(id:Int):OrderDbEntity?

    @Insert
    fun createOrder(orderDbEntity: OrderDbEntity)

    @Update(entity = OrderDbEntity::class)
    fun updateOrder(orderDbEntity: OrderDbEntity)

    @Query("SELECT Orders.* FROM Orders JOIN plant ON Orders.plant_id = plant.id WHERE plant.shop_id = :shop_id1; ")
    fun getOrdersByShopId(shop_id1:Int):List<OrderDbEntity?>?

    @Query("SELECT shop.* FROM shop JOIN plant ON shop.id = plant.shop_id JOIN Orders ON plant.id = Orders.plant_id WHERE Orders.id = :orderId")
    fun getShopIdByOrderId(orderId:Int):ShopDbEntity

    @Query("Select * from orders where user_id=:userId")
    fun getOrdersByUserId(userId:Int):List<OrderDbEntity?>?

}