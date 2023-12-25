package com.example.sellseeds.model.orders

import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.UserConverters
import com.example.sellseeds.model.plants.PlantDao
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopDao
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserDao

class OrdersRepositoryRealization(val ordersDao: OrdersDao ,val plantsDao: PlantDao ,val userCurrentId: UserCurrentId ,val shopCurrentId: ShopCurrentId ,val userDao: UserDao ,val shopDao: ShopDao):OrdersRepository {
    override suspend fun getAllOrders(): List<Orders>? {
        return ordersDao.getAllOrders()?.map { OrderDbEntity -> OrderDbEntity.toOrder() }
    }

    override suspend fun getOrderById(id: Int): Orders {
        return ordersDao.getOrderById(id)!!.toOrder()
    }

    override suspend fun createOrder(orders: Orders) {
        ordersDao.createOrder(OrderDbEntity.fromOrder(orders))
    }

    override suspend fun updateOrder(orders: Orders) {
        ordersDao.updateOrder(OrderDbEntity.fromOrder(orders))
    }

    override suspend fun getOrdersByUserId(userId: Int): List<Orders> {
        val t =ordersDao.getOrdersByUserId(userId) ?: return listOf()
        return  t.map { OrderDbEntity ->OrderDbEntity!!.toOrder() }
    }

    override suspend fun getOrdersByShopId(shop_id1: Int): List<Orders> {
        val sss =ordersDao.getOrdersByShopId(shop_id1) ?: return listOf()
        return  sss.map{ OrderDbEntity -> OrderDbEntity!!.toOrder()}

    }

    override suspend fun getShopByOrderId(orderId: Int): Shop {
        return ordersDao.getShopIdByOrderId(orderId).toShop()

    }

    override suspend fun getUserByOrderId(orderID: Int): User {
        return ordersDao.getUserByOrderId(orderID).toUser()
    }

    override suspend fun getPlantByOrderId(orderID: Int): Seed {
        return ordersDao.getPlantByOrderId(orderID).toSeed()
    }

     override suspend fun getOrdersbyOrderStatus(userId: Int): List<Orders> {
        val s =ordersDao.getOrdersbyOrderStatus(userId) ?: return listOf()

       return  s.map{ OrderDbEntity -> OrderDbEntity.toOrder()}
    }

    override suspend fun getOrdersbyOrderStatus_decr(userId: Int): List<Orders> {
        val s =ordersDao.getOrdersbyOrderStatus_decr(userId) ?: return listOf()

        return  s.map{ OrderDbEntity -> OrderDbEntity.toOrder()}
    }

    override fun getOrdersbyOrderStatus_byShopID(shopId: Int): List<Orders> {

        val s =ordersDao.getOrdersbyOrderStatus_byShopID(shopId) ?: return listOf()

        return s.map { OrderDbEntity -> OrderDbEntity.toOrder() }    }

    override fun getOrdersbyOrderStatus_byShopId_decr(shopId: Int): List<Orders> {
        val s =ordersDao.getOrdersbyOrderStatus_byShopId_decr(shopId) ?: return listOf()

        return s.map { OrderDbEntity -> OrderDbEntity.toOrder() }
    }
}