package com.example.sellseeds.model.orders

import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.UserConverters
import com.example.sellseeds.model.plants.PlantDao
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserDao

class OrdersRepositoryRealization(val ordersDao: OrdersDao ,val plantsDao: PlantDao ,val userCurrentId: UserCurrentId ,val shopCurrentId: ShopCurrentId ,val userDao: UserDao ,val shopDao: ShopDao):OrdersRepository {
    override fun getAllOrders(): List<Orders>? {
        return ordersDao.getAllOrders()?.map { OrderDbEntity -> OrderDbEntity.toOrder() }
    }

    override fun getOrderById(id: Int): Orders {
        return ordersDao.getOrderById(id)!!.toOrder()
    }

    override fun createOrder(orders: Orders) {
        ordersDao.createOrder(OrderDbEntity.fromOrder(orders))
    }

    override fun updateOrder(orders: Orders) {
        ordersDao.updateOrder(OrderDbEntity.fromOrder(orders))
    }

    override fun getOrdersByUserId(userId: Int): List<Orders> {
        val t =ordersDao.getOrdersByUserId(userId) ?: return listOf()
        return  t.map { OrderDbEntity ->OrderDbEntity!!.toOrder() }
    }

    override fun getOrdersByShopId(shop_id1: Int): List<Orders>? {
        return  ordersDao.getOrdersByShopId(shop_id1)!!.map{ OrderDbEntity -> OrderDbEntity!!.toOrder()}

    }

    override fun getShopByOrderId(orderId: Int): Shop {
        return ordersDao.getShopIdByOrderId(orderId).toShop()

    }
}