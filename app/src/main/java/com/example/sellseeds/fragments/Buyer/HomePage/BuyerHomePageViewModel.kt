package com.example.sellseeds.fragments.Buyer.HomePage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.adapters.OrdersAdapter
import com.example.sellseeds.adapters.ShopsAdapter
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.orders.OrdersRepository
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserRepository

class BuyerHomePageViewModel(val shopRepository:ShopRepository, val userRepository: UserRepository, val userCurrentId: UserCurrentId ,val ordersRepository: OrdersRepository):ViewModel() {
    val userId =MutableLiveData<Int>()
    val userList = MutableLiveData<List<User>>()
    val currentUser =MutableLiveData<User>()

    val shopsAdapter =MutableLiveData<ShopsAdapter>()
    val ordersAdapter =MutableLiveData<OrdersAdapter>()
    suspend fun getCurrentId():Int{
        return userCurrentId.getCurrentUserId()
    }
    suspend fun getAllShops(){
        shops.postValue( shopRepository.getAllShops().toMutableList())
    }

    suspend fun getAllMyOrders(){
       orders.postValue(ordersRepository.getOrdersByUserId(userCurrentId.getCurrentUserId()).toMutableList())
    }
    suspend fun getCurrentUser(){
        val id =userCurrentId.getCurrentUserId()
        Log.d("2qwewq", id.toString())
        val user =userRepository.getUserById(id)
         currentUser.postValue(user)
    }
    val shops = MutableLiveData<MutableList<Shop>>()
    val orders =MutableLiveData<MutableList<Orders>>()
    val isShopselected =MutableLiveData<Boolean>()

    suspend fun getShopsByName(){
        shops.postValue(shopRepository.getShopsByName().toMutableList())
    }
    suspend fun getShopsByName_decr(){
        shops.postValue(shopRepository.getShopsByName_decr().toMutableList())
    }

    suspend fun getOrdersbyOrderStatus(userId:Int){
        orders.postValue(ordersRepository.getOrdersbyOrderStatus(userId).toMutableList())
    }

    suspend fun getOrdersbyOrderStatus_decr(userId:Int) {
        orders.postValue(ordersRepository.getOrdersbyOrderStatus_decr(userId).toMutableList())

    }
    }




