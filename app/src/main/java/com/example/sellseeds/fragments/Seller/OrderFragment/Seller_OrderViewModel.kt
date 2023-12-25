package com.example.sellseeds.fragments.Seller.OrderFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.orders.OrdersRepository
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.user.UserRepository

class Seller_OrderViewModel(val ordersRepository: OrdersRepository,
val shopRepository: ShopRepository,
    val userRepository: UserRepository
):ViewModel() {

    val user =MutableLiveData<User>()
    val shop =MutableLiveData<Shop>()
    val plant =MutableLiveData<Seed>()



    val order =MutableLiveData<Orders>()

    suspend fun  toDeliver(order:Orders){
        ordersRepository.updateOrder(order)
    }



    suspend fun getShopByOrder(order:Orders){
        shop.postValue(ordersRepository.getShopByOrderId(order.id))

    }


    suspend fun getUserByOrderId(orderId :Int){
        val s =ordersRepository.getUserByOrderId(orderId)
        Log.d("2223212",s.toString())

        user.postValue(s)
    }
     suspend fun getPlantByOrderId(orderID:Int){
         plant.postValue(ordersRepository.getPlantByOrderId(orderID))

     }




}