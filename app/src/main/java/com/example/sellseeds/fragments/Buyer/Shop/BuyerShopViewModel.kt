package com.example.sellseeds.fragments.Buyer.Shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.model.plants.PlantRepositoryRealization
import com.example.sellseeds.model.plants.PlantsRepository
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.user.UserCurrentId

class BuyerShopViewModel(val currentId: UserCurrentId ,val plantsRepository: PlantsRepository ,val shopRepository: ShopRepository): ViewModel() {
    val currentId1 =MutableLiveData<Int>()
    val plants =MutableLiveData<List<Seed>>()

    suspend fun getCurrentUserId(){
        currentId1.postValue(currentId.getCurrentUserId())
    }



    suspend fun getAllPlantsOfThisShop(shop: Shop){
       plants.postValue( plantsRepository.getPlantsByShopId(shop_id = shop.id))

    }
}