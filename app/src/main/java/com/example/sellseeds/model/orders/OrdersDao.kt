package com.example.sellseeds.model.orders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.entities.ShopDbEntity
import com.example.sellseeds.model.user.UserDao
import com.example.sellseeds.model.user.entities.UserDbEntity

@Dao
interface OrdersDao {
    @Query("Select * from orders")
    suspend fun getAllOrders():List<OrderDbEntity>?

    @Query(" Select * from orders where id =:id" )
    suspend fun getOrderById(id:Int):OrderDbEntity?

    @Insert
    suspend fun createOrder(orderDbEntity: OrderDbEntity)

    @Update(entity = OrderDbEntity::class)
    suspend fun updateOrder(orderDbEntity: OrderDbEntity)

    @Query("SELECT Orders.* FROM Orders JOIN plant ON Orders.plant_id = plant.id WHERE plant.shop_id = :shop_id1; ")
    suspend fun getOrdersByShopId(shop_id1:Int):List<OrderDbEntity?>?

    @Query("SELECT shop.* FROM shop JOIN plant ON shop.id = plant.shop_id JOIN Orders ON plant.id = Orders.plant_id WHERE Orders.id = :orderId")
    suspend fun getShopIdByOrderId(orderId:Int):ShopDbEntity

    @Query("Select * from orders where user_id=:userId")
    suspend fun getOrdersByUserId(userId:Int):List<OrderDbEntity?>?
    @Query("SELECT user.* FROM user JOIN Orders ON user.id = Orders.user_id WHERE Orders.id = :orderID")
    suspend fun getUserByOrderId(orderID:Int):UserDbEntity
@Query("SELECT plant.* FROM plant JOIN Orders ON plant.id = Orders.plant_id WHERE Orders.id =:orderID ")
    suspend fun getPlantByOrderId(orderID:Int):PlantDbEntity

@Query("SELECT * FROM Orders WHERE user_id = :userId ORDER BY CASE WHEN Orders.status like 'Ca%' THEN 1 WHEN Orders.status like 'I%' THEN 2 WHEN Orders.status like 'Co%' THEN 3 end")
    fun getOrdersbyOrderStatus(userId:Int):List<OrderDbEntity>?

    @Query("SELECT * FROM Orders WHERE user_id = :userId ORDER BY CASE WHEN Orders.status like 'Co%' THEN 1 WHEN Orders.status  like 'I%' THEN 2 WHEN Orders.status like 'Ca%' THEN 3    END")
    fun getOrdersbyOrderStatus_decr(userId:Int):List<OrderDbEntity>?


    @Query("SELECT Orders.* FROM Orders JOIN plant ON Orders.plant_id = plant.id WHERE plant.shop_id = :shopId ORDER BY Orders.id ASC;")
    fun getOrdersbyOrderStatus_byShopID(shopId:Int):List<OrderDbEntity>?

    @Query("SELECT Orders.* FROM Orders JOIN plant ON Orders.plant_id = plant.id WHERE plant.shop_id = :shopId ORDER BY Orders.id DESC;")
    fun getOrdersbyOrderStatus_byShopId_decr(shopId:Int):List<OrderDbEntity>?





}