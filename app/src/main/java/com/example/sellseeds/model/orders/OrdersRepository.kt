package com.example.sellseeds.model.orders

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.shop.entities.ShopDbEntity

interface OrdersRepository {

    fun getAllOrders():List<Orders>?

    fun getOrderById(id:Int):Orders

    fun createOrder(orders: Orders)

    fun updateOrder(orders: Orders)

//    @Query("Select * from orders where ")
//    fun getOrdersByShopId(id:Int):List<OrderDbEntity?>?

    fun getOrdersByUserId(userId:Int):List<Orders>

    fun getOrdersByShopId(shop_id1:Int):List<Orders>?

    fun getShopByOrderId(orderId:Int): Shop
}