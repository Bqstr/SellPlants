package com.example.sellseeds.fragments.Buyer.HomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.user.UserRepository

class BuyerHomePageViewModel(val shopRepository:ShopRepository, val userRepository: UserRepository, val userCurrentId: UserCurrentId):ViewModel() {
    val userId =MutableLiveData<Int>()
    val userList = MutableLiveData<List<User>>()
    val currentUser =MutableLiveData<User>()
    suspend fun getCurrentId():Int{
        return userCurrentId.getCurrentUserId()
    }
    suspend fun getAllShops(){
        shops.postValue( shopRepository.getAllShops().toMutableList())
    }
    suspend fun getCurrentUser(){
        val id =userCurrentId.getCurrentUserId()
        val user =userRepository.getUserById(id)
         currentUser.postValue(user)
    }
    val shops = MutableLiveData<MutableList<Shop>>()
    val orders =MutableLiveData<MutableList<Orders>>()
    val isShopselected =MutableLiveData<Boolean>()




}