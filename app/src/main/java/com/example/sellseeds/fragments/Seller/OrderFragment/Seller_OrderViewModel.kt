package com.example.sellseeds.fragments.Seller.OrderFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.model.orders.OrdersRepository

class Seller_OrderViewModel(val ordersRepository: OrdersRepository):ViewModel() {


    val order =MutableLiveData<Orders>()

    suspend fun  toDeliver(order:Orders){
        ordersRepository.updateOrder(order)
    }


}