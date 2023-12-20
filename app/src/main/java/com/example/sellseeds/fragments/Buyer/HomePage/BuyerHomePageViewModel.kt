package com.example.sellseeds.fragments.Buyer.HomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.fragments.Buyer.registerUser.UserCurrentId
import com.example.sellseeds.model.user.RoomUserRepository
import com.example.sellseeds.model.user.UserRepository

class BuyerHomePageViewModel(val userRepository: UserRepository, val userCurrentId: UserCurrentId):ViewModel() {
    val userId =MutableLiveData<Int>()
    suspend fun getCurrentId():Int{
        return userCurrentId.getCurrentUserId()
    }
    val shops = MutableLiveData<MutableList<Shop>>()
    val orders =MutableLiveData<MutableList<Orders>>()
    val isShopselected =MutableLiveData<Boolean>()




}