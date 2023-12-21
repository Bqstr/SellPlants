package com.example.sellseeds.fragments.Seller.HomePage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.plants.PlantsRepository
import com.example.sellseeds.model.plants.entenies.PlantDbEntity
import com.example.sellseeds.model.shop.ShopCurrentId
import com.example.sellseeds.model.shop.ShopRepository

class SellerHomePageViewModel(val shopRepository: ShopRepository ,val plantsRepository: PlantsRepository ,val currentId: ShopCurrentId): ViewModel() {

    val shop_currentId =MutableLiveData<Int>()
    private var _orderList =MutableLiveData<MutableList<Orders>>()
    var orderList :LiveData<MutableList<Orders>> =_orderList


    private var _productList =MutableLiveData<MutableList<Seed>>()
    var productList :LiveData<MutableList<Seed>> =_productList

    private var _isProductPressed =MutableLiveData<Boolean>()
    var isProductPressed :LiveData<Boolean> =_isProductPressed


    init{

      //  _productList.postValue(createData())
       // _orderList.postValue(createOrderData())
        _isProductPressed.postValue(true)


    }
    val plantList=MutableLiveData<List<Seed>>()


    fun addProduct(product:Seed){
        if(productList.value!=null){
           val l =productList.value!!
            l.add(product)
            _productList.postValue(l)
        }
        else{
            val list = mutableListOf<Seed>()
            list.add(product)
            _productList.postValue(list)
        }
    }
    suspend fun getCurrentId(){
        Log.d("xzxzxzxzx" ,currentId.getCurrentId().toString())

        shop_currentId.postValue(currentId.getCurrentId())
    }

    suspend fun getAllMyPlants(){
        Log.d("1111111111111",plantsRepository.getPlantsByShopId(currentId.getCurrentId()).toString())

        if(plantsRepository.getPlantsByShopId(currentId.getCurrentId())==null){
            plantList.postValue(mutableListOf())
        }
        else{
            plantList.postValue(plantsRepository.getPlantsByShopId(currentId.getCurrentId())!!.map { PlantDbEntity ->PlantDbEntity!!.toSeed() })
        }
    }

    fun changeAdapter(b:Boolean){
        _isProductPressed.postValue(b)
    }
//
//    private fun createSellerData(): Shop {
//        return (Shop(
//            0,
//            "Hydra",
//            "3_head_gydra@gmai.com",
//            "jail",
//            "+666",
//            createData(),
//            createOrderData(),
//            R.drawable.img_rectangle19,
//            Rating.FIVE_STAR,
//            100,"123"
//        ))
//    }
//
//
//    private fun createData(): MutableList<Seed> {
//        val mutableList = mutableListOf<Seed>(
//            Seed(
//                0,
//                "Olive Tree",
//                "description",
//                1000,
//                R.drawable.img_rectangle12,
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                1,
//                "Money Tree",
//                "description",
//                2000,
//                listOf(R.drawable.img_rectangle12_1),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                2,
//                "Faux Palm Tree",
//                "description",
//                3000,
//                listOf(R.drawable.img_rectangle12_108x110),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                3,
//                "Kek Tree",
//                "description",
//                999,
//                listOf(R.drawable.img_rectangle12_2),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                0,
//                "Olive Tree",
//                "description",
//                1000,
//                listOf(R.drawable.img_rectangle12),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//            Seed(
//                2,
//                "Faux Palm Tree",
//                "description",
//                3000,
//                listOf(R.drawable.img_rectangle12_108x110),
//                Category.SmallPlant,
//                1,
//                Discount(true, 0.2)
//            ),
//
//
//            )
//        return mutableList
//
//    }
//
//    fun createOrderData(): MutableList<Orders> {
//        val examplePlant = Seed(
//            0,
//            "Olive Tree",
//            "description",
//            1000,
//            listOf(R.drawable.img_rectangle12),
//            Category.SmallPlant,
//            1,
//            Discount(true, 0.2)
//        )
//        val exampleShop = Shop(0,"1","123","123","123",createData() , mutableListOf(),R.drawable.img_rectangle19,  Rating.FOUR_STAR ,100,"123")
//        val exampleUser =
//            User(0, "USER_NAME", "email_user@gmail.com", "USER_ADRESS", "+777777777777777", mutableListOf<Orders>(),"123")
//
//        var mut = mutableListOf(
//            Orders(
//                123,
//                1000,
//                examplePlant,
//                10,
//                adress = "ADRESS",
//                shop = exampleShop,
//                buyer = exampleUser,
//                date = 12312,
//                status = OrderStatus.InProgress
//            ),
//            Orders(
//                124,
//                2000,
//                examplePlant,
//                20,
//                adress = "ADRESS",
//                shop = exampleShop,
//                buyer = exampleUser,
//                date = 12312123,
//                status = OrderStatus.Completed
//            ),
//
//            Orders(
//                125,
//                6000,
//                examplePlant,
//                10,
//                adress = "ADRESS",
//                shop = exampleShop,
//                buyer = exampleUser,
//                date = 12312,
//                status = OrderStatus.Canceled
//            )
//        )
//
//        return mut
//    }
//

}

