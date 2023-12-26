package com.example.sellseeds.fragments.Seller.HomePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.R
import com.example.sellseeds.adapters.OrdersAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.orders.OrdersRepository
import com.example.sellseeds.model.plants.PlantsRepository
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopRepository

class SellerHomePageViewModel(val shopRepository: ShopRepository ,val plantsRepository: PlantsRepository ,val currentId: ShopCurrentId ,val ordersRepository: OrdersRepository): ViewModel() {

    val plantsSortState = MutableLiveData<String>()
    val ordersSortState = MutableLiveData<String>()

    val shop_currentId = MutableLiveData<Int>()
    var orderList= MutableLiveData<MutableList<Orders>>()

    val currentShop = MutableLiveData<Shop>()


    private var _productList = MutableLiveData<MutableList<Seed>>()
    var productList: LiveData<MutableList<Seed>> = _productList

    private var _isProductPressed = MutableLiveData<Boolean>()
    var isProductPressed: LiveData<Boolean> = _isProductPressed

    init {

        //  _productList.postValue(createData())
        // _orderList.postValue(createOrderData())
        _isProductPressed.postValue(true)


    }

    val plantList = MutableLiveData<List<Seed>>()


    fun addProduct(product: Seed) {
        if (productList.value != null) {
            val l = productList.value!!
            l.add(product)
            _productList.postValue(l)
        } else {
            val list = mutableListOf<Seed>()
            list.add(product)
            _productList.postValue(list)
        }
    }

    suspend fun getAllMyOrders() {
        orderList.postValue(
            ordersRepository.getOrdersByShopId(currentId.getCurrentId())!!.toMutableList()
        )

    }

    suspend fun getCurrentId() {
        Log.d("xzxzxzxzx", currentId.getCurrentId().toString())

        shop_currentId.postValue(currentId.getCurrentId())
    }

    suspend fun getAllOrders(): List<Orders> {
        val s = ordersRepository.getAllOrders()
        if (s == null) {
            return listOf()
        }
        return ordersRepository.getAllOrders()!!
    }

    suspend fun getAllMyPlants() {
        Log.d(
            "1111111111111",
            plantsRepository.getPlantsByShopId(currentId.getCurrentId()).toString()
        )

        if (plantsRepository.getPlantsByShopId(currentId.getCurrentId()) == null) {
            plantList.postValue(mutableListOf())
        } else {
            plantList.postValue(plantsRepository.getPlantsByShopId(currentId.getCurrentId()))
        }
    }

    suspend fun getShopbyId(id: Int) {
        currentShop.postValue(shopRepository.getShopById(id))
    }

    fun changeAdapter(b: Boolean) {
        _isProductPressed.postValue(b)
    }
    suspend  fun getPlantsByShopId_incr(){

        plantList.postValue(plantsRepository.getPlantsByShopId_incr(currentId.getCurrentId()))
    }
    suspend  fun getPlantsByShopId_decr(){
        plantList.postValue(plantsRepository.getPlantsByShopId_decr(currentId.getCurrentId()).toMutableList())

    }

    suspend  fun getOrdersByShopId_incr(){

        orderList.postValue(ordersRepository.getOrdersbyOrderStatus_byShopID(currentId.getCurrentId())?.toMutableList())
    }
    suspend  fun getOrdersByShopId_decr(){
        orderList.postValue(ordersRepository.getOrdersbyOrderStatus_byShopID(currentId.getCurrentId())?.toMutableList())

    }


    suspend fun getOrdersbyOrderStatus(){
        orderList.postValue(ordersRepository.getOrdersbyOrderStatus(currentId.getCurrentId()).toMutableList())
    }

    suspend fun getOrdersbyOrderStatus_decr() {
        orderList.postValue(ordersRepository.getOrdersbyOrderStatus_decr(currentId.getCurrentId()).toMutableList())

    }

}


