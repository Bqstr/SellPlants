package com.example.sellseeds.fragments.Buyer.Shop

import android.util.Log
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
    val plantsSortState =MutableLiveData<String>()

    suspend fun getCurrentUserId(){
        currentId1.postValue(currentId.getCurrentUserId())
    }



    suspend fun getAllPlantsOfThisShop(shop: Shop){
        Log.d("12321sss","${shop.id.toString() }   perve")

        currentId1.postValue(shop.id)
       plants.postValue( plantsRepository.getPlantsByShopId(shop_id = shop.id))

    }



    suspend  fun getPlantsByName_decr() {
        Log.d("12321sss",currentId1.value!!.toString())

        plants.postValue(plantsRepository.getPlantByCategory_decr(currentId1.value!!))

    }

    suspend fun getPlantsByName_incr() {
        Log.d("12321sss",currentId1.value!!.toString())

        plants.postValue(plantsRepository.getPlantByCategory_incr(currentId1.value!!))
    }
    suspend fun getPlantsByCatefory_incr() {
        Log.d("12321sss",currentId1.value!!.toString())
        plants.postValue(plantsRepository.getPlantByCategory_incr(currentId1.value!!))
    }

    suspend  fun getPlantsByCatefory_decr() {
        Log.d("12321sss",currentId1.value!!.toString())

        plants.postValue(plantsRepository.getPlantByCategory_decr(currentId1.value!!))

    }

    suspend fun getAllMyPlants() {
        Log.d("12321sss",plantsRepository.getAllPlants().toString())

        plants.postValue(plantsRepository.getAllMyPlant(currentId1.value!!))
        //plants.postValue(plantsRepository.getAllPlants())

    }
}