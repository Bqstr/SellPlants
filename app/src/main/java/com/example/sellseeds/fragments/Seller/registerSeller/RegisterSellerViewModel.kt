package com.example.sellseeds.fragments.Seller.registerSeller

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.fragments.Buyer.registerUser.UserCurrentId
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopRepository


class RegisterSellerViewModel(
    private val shopRepository: ShopRepository,
    private val currentId: ShopCurrentId
): ViewModel() {

    val naviagate_toMain =MutableLiveData<Boolean>()
//    val state

    suspend fun registerShop(shop: Shop){
        shopRepository.createShop(shop)
    }
    suspend fun getCurrentId():Int{
        return currentId.getCurrentId()
    }







}
