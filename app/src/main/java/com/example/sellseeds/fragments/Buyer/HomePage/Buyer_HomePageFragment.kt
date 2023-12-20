package com.example.sellseeds.fragments.Buyer.HomePage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.OrdersAdapter
import com.example.sellseeds.R
import com.example.sellseeds.SeedsAdapter
import com.example.sellseeds.ShopsAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User

import com.example.sellseeds.databinding.ActivityBuyerHomepageBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Buyer_HomePageFragment : Fragment() {
    lateinit var shopAdapter:ShopsAdapter
    lateinit var ordersAdapter: OrdersAdapter
    lateinit var binding: ActivityBuyerHomepageBinding
    var isShopsSelected:Boolean =true
    val viewModel by viewModelCreator{BuyerHomePageViewModel(Repositories.accountsRepository, Repositories.userCurrentId)  }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ActivityBuyerHomepageBinding.inflate(layoutInflater)
        val layoutManager= LinearLayoutManager(context)

        shopAdapter = ShopsAdapter(findNavController(), context)
        ordersAdapter = OrdersAdapter(findNavController(), context ,true)
        binding.abobus.adapter =shopAdapter
        binding.abobus.layoutManager =layoutManager

        shopAdapter.shops = mutableListOf(createSellerData())
        ordersAdapter.orders =createOrderData()
        shopsPressed()

        binding.imageVectorFour.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {

          viewModel.userId.postValue(viewModel.getCurrentId())

        }
        }



        viewModel.shops.observe(viewLifecycleOwner) {
            shopAdapter.shops = it
        }
        viewModel.orders.observe(viewLifecycleOwner) {
            ordersAdapter.orders = it
        }
        viewModel.isShopselected.observe(viewLifecycleOwner){
            isShopsSelected =it
        }
        binding.btnShopsOne.setOnClickListener{
            if(!isShopsSelected) {
                shopsPressed()
            }
        }
        binding.btnOOrders.setOnClickListener{
            if(isShopsSelected){
                ordersPressed()
            }
        }
    viewModel.userId.observe(viewLifecycleOwner){
        Toast.makeText(context , "$it <-  eto" , Toast.LENGTH_LONG).show()

    }





        return binding.root
    }

    private fun ordersPressed() {

        binding.abobus.adapter =ordersAdapter
        val layoutManager= LinearLayoutManager(context)

        binding.abobus.layoutManager =layoutManager
        viewModel.isShopselected.postValue(false)

        binding.btnShopsOne.setBackgroundColor(getResources().getColor(R.color.white))
        binding.btnOOrders.setBackgroundColor(getResources().getColor(R.color.light_green_800))

    }

    private fun shopsPressed() {

        binding.abobus.adapter =shopAdapter
        val layoutManager= LinearLayoutManager(context)

        binding.abobus.layoutManager =layoutManager
        viewModel.isShopselected.postValue(true)

        binding.btnShopsOne.setBackgroundColor(getResources().getColor(R.color.light_green_800))
        binding.btnOOrders.setBackgroundColor(getResources().getColor(R.color.white))




    }
    private fun createSellerData(): Shop {
        return (Shop(
            0,
            "Hydra",
            "3_head_gydra@gmai.com",
            "jail",
            "+666",
            createData(),
            createOrderData(),
            R.drawable.img_rectangle19,
            Rating.FIVE_STAR,
            100,"13"
        ))
    }


    private fun createData(): MutableList<Seed> {
        val mutableList = mutableListOf<Seed>(
            Seed(
                0,
                "Olive Tree",
                "description",
                1000,
                listOf(R.drawable.img_rectangle12),
                Category.SmallPlant,
                1,
                Discount(true, 0.2)
            ),
            Seed(
                1,
                "Money Tree",
                "description",
                2000,
                listOf(R.drawable.img_rectangle12_1),
                Category.SmallPlant,
                1,
                Discount(true, 0.2)
            ),
            Seed(
                2,
                "Faux Palm Tree",
                "description",
                3000,
                listOf(R.drawable.img_rectangle12_108x110),
                Category.SmallPlant,
                1,
                Discount(true, 0.2)
            ),
            Seed(
                3,
                "Kek Tree",
                "description",
                999,
                listOf(R.drawable.img_rectangle12_2),
                Category.SmallPlant,
                1,
                Discount(true, 0.2)
            ),
            Seed(
                0,
                "Olive Tree",
                "description",
                1000,
                listOf(R.drawable.img_rectangle12),
                Category.SmallPlant,
                1,
                Discount(true, 0.2)
            ),
            Seed(
                2,
                "Faux Palm Tree",
                "description",
                3000,
                listOf(R.drawable.img_rectangle12_108x110),
                Category.SmallPlant,
                1,
                Discount(true, 0.2)
            ),


            )
        return mutableList

    }

    fun createOrderData(): MutableList<Orders> {
        val examplePlant = Seed(
            0,
            "Olive Tree",
            "description",
            1000,
            listOf(R.drawable.img_rectangle12),
            Category.SmallPlant,
            1,
            Discount(true, 0.2)
        )
        val exampleShop = Shop(0,"1","123","123","123",createData() , mutableListOf(),R.drawable.img_rectangle19 , Rating.FOUR_STAR ,100,"123")
        val exampleUser =
            User(0, "USER_NAME", "email_user@gmail.com", "USER_ADRESS", "+777777777777777", mutableListOf<Orders>(),"123")

        var mut = mutableListOf(
            Orders(
                123,
                1000,
                examplePlant,
                10,
                adress = "ADRESS",
                shop = exampleShop,
                buyer = exampleUser,
                date = 12312,
                status = OrderStatus.InProgress
            ),
            Orders(
                124,
                2000,
                examplePlant,
                20,
                adress = "ADRESS",
                shop = exampleShop,
                buyer = exampleUser,
                date = 12312123,
                status = OrderStatus.Completed
            ),

            Orders(
                125,
                6000,
                examplePlant,
                10,
                adress = "ADRESS",
                shop = exampleShop,
                buyer = exampleUser,
                date = 12312,
                status = OrderStatus.Canceled
            )
        )

        return mut
    }


}