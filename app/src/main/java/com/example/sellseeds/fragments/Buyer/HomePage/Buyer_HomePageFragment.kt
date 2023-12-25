package com.example.sellseeds.fragments.Buyer.HomePage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.R
import com.example.sellseeds.adapters.OrdersAdapter
import com.example.sellseeds.adapters.ShopsAdapter
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.databinding.ActivityBuyerHomepageBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Buyer_HomePageFragment : Fragment() {
    lateinit var shopAdapter: ShopsAdapter
    lateinit var ordersAdapter: OrdersAdapter
    var shop_sortState ="by_id"
    var orders_sortState ="by_id"

    lateinit var binding: ActivityBuyerHomepageBinding
    var isShopsSelected: Boolean = true
    val viewModel by viewModelCreator {
        BuyerHomePageViewModel(
            Repositories.shopRepository,
            Repositories.accountsRepository,
            Repositories.userCurrentId,
            Repositories.ordersRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ActivityBuyerHomepageBinding.inflate(layoutInflater)
        val layoutManager = LinearLayoutManager(context)
        //lifecycleScope.launch(Dispatchers.IO) {
        shopAdapter = ShopsAdapter(findNavController(), context)
        ordersAdapter = OrdersAdapter(
            findNavController(),
            requireContext(),
            true
        )
        viewModel.shopsAdapter.postValue(shopAdapter)
        viewModel.ordersAdapter.postValue(ordersAdapter)
        binding.abobus.adapter = shopAdapter
        binding.abobus.layoutManager = layoutManager
        shopsPressed()


        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getAllShops()
            viewModel.getCurrentUser()
            viewModel.getAllMyOrders()
        }

        viewModel.currentUser.observe(viewLifecycleOwner) {
            binding.txtRavijaganiOne.text = it.name
            binding.txtEmailOne.text = it.email

        }








        binding.imageVectorFour.setOnClickListener {
           // lifecycleScope.launch(Dispatchers.IO) {

                //viewModel.userId.postValue(viewModel.getCurrentId())
          //  }
            findNavController().navigateUp()
        }





        viewModel.shops.observe(viewLifecycleOwner) {
            Log.d("whattt", it.toString())

            shopAdapter.shops = it
            shopAdapter.notifyDataSetChanged()
        }
        viewModel.orders.observe(viewLifecycleOwner) {
            Log.d("whattt", it.toString())
            ordersAdapter.orders = it
            ordersAdapter.notifyDataSetChanged()

        }
        viewModel.isShopselected.observe(viewLifecycleOwner) {
            isShopsSelected = it
        }
        binding.btnShopsOne.setOnClickListener {
            if (!isShopsSelected) {
                shopsPressed()
            }
        }
        binding.btnOOrders.setOnClickListener {
            if (isShopsSelected) {
                ordersPressed()
            }
        }

        viewModel.userId.observe(viewLifecycleOwner) {

            Log.d("asasasasasas", it.toString())


        }
        viewModel.userList.observe(viewLifecycleOwner) {
            for (a in 0 until it.size) {
                Log.d("aaaaaaaaaaaaaaaaaaa", "${it[a].id}   ${it[a].email}  ${it[a].password}")
            }
        }

        val searchView =binding.etGroupTwentyThree as EditText
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()
                if(newText.isNotBlank()){
                    onChange(newText)

                }
                else{

                    if(shop_sortState=="by_id"){
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getShopsByName()
                        }
                    }
                    else if(shop_sortState=="by_name_incr"){
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getShopsByName_decr()
                        }
                    }
                    else{
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getAllShops()
                        }
                    }
                }



            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })
        binding.btnGroupTwelve.setOnClickListener {
            if(isShopsSelected) {
                when (shop_sortState) {
                    "by_id" -> {
                        Toast.makeText(context, "by_name_incr", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getShopsByName()
                        }
                        shop_sortState = "by_name_incr"

                    }

                    "by_name_incr" -> {
                        Toast.makeText(context, "by_name_dcr", Toast.LENGTH_SHORT).show()

                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getShopsByName_decr()
                        }
                        shop_sortState = "by_name_dcr"


                    }

                    "by_name_dcr" -> {
                        Toast.makeText(context, "by_id", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getAllShops()
                        }
                        shop_sortState = "by_id"
                    }

                }
            }
            else {
                when (orders_sortState) {
                    "by_id" -> {
                        Toast.makeText(context, "by_category_from_big", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch (Dispatchers.IO){
                            viewModel.getOrdersbyOrderStatus(viewModel.currentUser.value!!.id)
                        }
                        orders_sortState ="by_category_from_big"

                    }
                    "by_category_from_big" -> {
                        Toast.makeText(context, "by_category_from_small", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch (Dispatchers.IO){
                            viewModel.getOrdersbyOrderStatus_decr(viewModel.currentUser.value!!.id)
                        }
                        orders_sortState ="by_category_from_small"

                    }

                    "by_category_from_small" -> {
                        Toast.makeText(context, "by_id", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch (Dispatchers.IO){
                            viewModel.getAllMyOrders()
                        }
                        orders_sortState ="by_id"


                    }
                }
            }
        }







        return binding.root
    }


    private fun ordersPressed() {

             binding.abobus.adapter = ordersAdapter


        val layoutManager = LinearLayoutManager(context)

        binding.abobus.layoutManager = layoutManager
        viewModel.isShopselected.postValue(false)





        binding.btnShopsOne.setBackgroundColor(getResources().getColor(R.color.white))
        binding.btnOOrders.setBackgroundColor(getResources().getColor(R.color.light_green_800))
}





    private fun shopsPressed() {




                binding.abobus.adapter = shopAdapter





                val layoutManager = LinearLayoutManager(context)

                binding.abobus.layoutManager = layoutManager
                viewModel.isShopselected.postValue(true)



        binding.btnShopsOne.setBackgroundColor(getResources().getColor(R.color.light_green_800))
        binding.btnOOrders.setBackgroundColor(getResources().getColor(R.color.white))








    }



     fun onChange(txt: String?) {
        val shopList =viewModel.shops.value
         val newList = mutableListOf<Shop>()
        if(txt!=null && shopList!=null){
            for(a in 0 until shopList.size){
                if(shopList.get(a).name.contains(txt)){
                    newList.add(shopList.get(a))
                }
            }
            viewModel.shops.postValue(newList)



        }

    }


}