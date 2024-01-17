package com.example.sellseeds.fragments.Seller.HomePage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.clearFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.adapters.OrdersAdapter

import com.example.sellseeds.R
import com.example.sellseeds.adapters.SeedsAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.databinding.ActivitySellerHomepageOneBinding
import com.example.sellseeds.fragments.Seller.AddProduct.ADD_PRODUCT_KEY
import com.example.sellseeds.fragments.Seller.OrderFragment.ActionOrder
import com.example.sellseeds.fragments.Seller.OrderFragment.BACK_FROM_ORDER
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val GO_SELLER_EDITPROFILE ="GO_SELLER_EDITPROFILE"
const val GO_ORDER_DETAILS ="GO_ORDER_DETAILS"
class SellerHomePageFragment : Fragment() {

lateinit var binding:ActivitySellerHomepageOneBinding

val viewModel by viewModelCreator { SellerHomePageViewModel(Repositories.shopRepository ,Repositories.plantsRepository ,Repositories.shopCurrentId ,Repositories.ordersRepository) }
var isProductPressed =true
    lateinit var productAdapter: SeedsAdapter
    lateinit var orderAdapter: OrdersAdapter

     var currentId =-1


    var plantsSortState ="by_id"
    var ordersSortState ="by_id"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =ActivitySellerHomepageOneBinding.inflate(layoutInflater)




        productAdapter = SeedsAdapter(findNavController(), context, layoutInflater)

         orderAdapter = OrdersAdapter(findNavController(),requireContext(), false )


        viewModel.shop_currentId.observe(viewLifecycleOwner){
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getShopbyId(it)
            }

            Log.d("xzxzxzxzx" ,it.toString())

        }
        viewModel.currentShop.observe(viewLifecycleOwner){

            binding.sellerName.text =it.name
            binding.txtEmail.text =it.email
        }


            lifecycleScope.launch (Dispatchers.IO){
                viewModel.getCurrentId()

            }
        if(arguments!=null){

            if(arguments?.getSerializable(ADD_PRODUCT_KEY)!=null){
                val product =arguments?.getSerializable(ADD_PRODUCT_KEY) as Seed
                viewModel.addProduct(product)
            }
            Log.d("111111111111111111",(arguments?.getSerializable(BACK_FROM_ORDER) as ActionOrder).orderId.toString())

            if(arguments?.getSerializable(BACK_FROM_ORDER)!=null){

                //viewModel.addProduct(product)
            }


        }
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getAllMyPlants()
        }
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getAllMyOrders()
        }
        viewModel.plantList.observe(viewLifecycleOwner) {

            productAdapter.seeds =it.toMutableList()
            productAdapter.notifyDataSetChanged()

        }

        viewModel.orderList.observe(viewLifecycleOwner){
            orderAdapter.orders =it
            orderAdapter.notifyDataSetChanged()
        }
        viewModel.plantsSortState.observe(viewLifecycleOwner) {
            plantsSortState =it
        }
        viewModel.ordersSortState.observe(viewLifecycleOwner) {
            ordersSortState =it
        }









        val searchView =binding.etGroupTwentyThree as EditText
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val newText = s.toString()
                if (newText.isNotBlank()) {
                    onChange(newText)

                } else {
                    if(plantsSortState=="by_id"){
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getPlantsByShopId_incr()
                        }
                    }
                    else if(plantsSortState=="by_name_incr"){
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getPlantsByShopId_decr()
                        }
                    }
                    else{
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getAllMyPlants()
                        }
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })





















        viewModel.isProductPressed.observe(viewLifecycleOwner,{
            Log.d("12312312312312321","changed")
            isProductPressed =it
        })
        viewModel.productList.observe(viewLifecycleOwner,{
            Log.d("dsfnjkasdbfjsabdljfksa",it.get(0).name)
            productAdapter.seeds =it
            productAdapter.notifyDataSetChanged()

        })




        binding.btnProduct.setOnClickListener{
            Log.d("12312312312312321",isProductPressed.toString())
            if(!isProductPressed) productPressed()
        }
        binding.btnOrders.setOnClickListener{
            Log.d("12312312312312321",isProductPressed.toString())

            if(isProductPressed) orderPressed()
        }

        productPressed()




        binding.floatingBtnFloatingactionbutton.setOnClickListener{
             findNavController().navigate(R.id.action_sellerHomePageFragment_to_addProductFragment)
        }











        binding.changeStatee.setOnClickListener {
            if(isProductPressed) {
                when (plantsSortState) {
                    "by_id" -> {
                        Toast.makeText(context, "by_name_incr", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getPlantsByShopId_incr()
                        }
                        plantsSortState = "by_name_incr"

                    }

                    "by_name_incr" -> {
                        Toast.makeText(context, "by_name_dcr", Toast.LENGTH_SHORT).show()

                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getPlantsByShopId_decr()
                        }
                        plantsSortState = "by_name_dcr"


                    }

                    "by_name_dcr" -> {
                        Toast.makeText(context, "by_id", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getAllMyPlants()
                        }
                        plantsSortState = "by_id"
                    }

                }
            }
            else {
                when (ordersSortState) {
                    "by_id" -> {
                        //Toast.makeText(context, "by_category_from_big", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch (Dispatchers.IO){
                            viewModel.getOrdersByShopId_incr()
                        }
                        ordersSortState ="by_category_from_big"

                    }
                    "by_category_from_big" -> {
                       // Toast.makeText(context, "by_category_from_small", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch (Dispatchers.IO){
                            viewModel.getOrdersByShopId_decr()
                        }
                        ordersSortState ="by_category_from_small"

                    }

                    "by_category_from_small" -> {
                       // Toast.makeText(context, "by_id", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch (Dispatchers.IO){
                            viewModel.getAllMyOrders()
                        }
                        ordersSortState ="by_id"


                    }
                }
            }
        }



        return binding.root
    }



    private fun productPressed() {
        binding.sellerHomePageRecyclerView.adapter =productAdapter
        val layoutManager =GridLayoutManager(context,2)
        binding.sellerHomePageRecyclerView.layoutManager =layoutManager



        viewModel.changeAdapter(true)
        binding.btnProduct.setBackgroundColor(getResources().getColor(R.color.light_green_800))
        binding.btnOrders.setBackgroundColor(getResources().getColor(R.color.white))
    }

    private fun orderPressed() {
       // orderAdapter = OrdersAdapter(findNavController(),context)
        binding.sellerHomePageRecyclerView.adapter =orderAdapter
        val layoutManager =LinearLayoutManager(context)
        binding.sellerHomePageRecyclerView.layoutManager =layoutManager
//



        viewModel.changeAdapter(false)

        binding.btnOrders.setBackgroundColor(getResources().getColor(R.color.light_green_800))
        binding.btnProduct.setBackgroundColor(getResources().getColor(R.color.white))

    }
    fun onChange(txt: String?) {
        val plantList =viewModel.plantList.value
        val newList = mutableListOf<Seed>()
        if(txt!=null && plantList!=null){
            for(a in 0 until plantList.size){
                if(plantList.get(a).name.contains(txt)){
                    newList.add(plantList.get(a))
                }
            }
            viewModel.plantList.postValue(newList)



        }

    }




    }

