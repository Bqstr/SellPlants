package com.example.sellseeds.model.orders

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.entities.UserDbEntity

interface OrdersRepository {

    suspend fun getAllOrders():List<Orders>?

    suspend fun getOrderById(id:Int):Orders

    suspend fun createOrder(orders: Orders)

    suspend fun updateOrder(orders: Orders)

//    @Query("Select * from orders where ")
//    fun getOrdersByShopId(id:Int):List<OrderDbEntity?>?

    suspend fun getOrdersByUserId(userId:Int):List<Orders>

    suspend fun getOrdersByShopId(shop_id1:Int):List<Orders>

    suspend fun getShopByOrderId(orderId:Int): Shop




    suspend fun getUserByOrderId(orderID:Int): User
    suspend fun getPlantByOrderId(orderID:Int): Seed

    suspend fun getOrdersbyOrderStatus(userId:Int):List<Orders>

    suspend fun getOrdersbyOrderStatus_decr(userId:Int):List<Orders>



    fun getOrdersbyOrderStatus_byShopID(shopId:Int):List<Orders>?

    fun getOrdersbyOrderStatus_byShopId_decr(shopId:Int):List<Orders>?
}