package com.example.sellseeds.fragments.Seller.AddProduct

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.model.plants.PlantsRepository
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopRepository

class AddProductViewModel(val plantsRepository: PlantsRepository ,val shop_currentId: ShopCurrentId ,shopRepository: ShopRepository): ViewModel() {

    val currentId =MutableLiveData<Int>()
    suspend fun addProduct( seed: Seed, ){
        plantsRepository.addPlant(seed)
    }
    suspend fun getCurrentShop_id(){
        Log.d("xzxzxzxzx" ,shop_currentId.getCurrentId().toString())
        return currentId.postValue(shop_currentId.getCurrentId())
    }




}