package com.example.sellseeds.fragments.Buyer.Shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.R
import com.example.sellseeds.adapters.BuyerShopDetailsAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop

import com.example.sellseeds.databinding.ActivityShopBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [BuyerShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuyerShopFragment : Fragment() {
    var plantsSortState ="by_id"
lateinit var adapter:BuyerShopDetailsAdapter
lateinit var binding:ActivityShopBinding
val viewModel by viewModelCreator{ BuyerShopViewModel(Repositories.userCurrentId ,Repositories.plantsRepository ,Repositories.shopRepository) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityShopBinding.inflate(layoutInflater)


         adapter = BuyerShopDetailsAdapter(findNavController(), context ,layoutInflater ,Repositories.ordersRepository ,Repositories.userCurrentId,Repositories.accountsRepository)
        binding.recyclerrr.adapter =adapter
        val layoutManager =LinearLayoutManager(context)
        binding.recyclerrr.layoutManager =layoutManager
//        adapter.plants.add(Seed(1 ,"a" ,"123" ,123 , R.drawable.faux_palm_tree ,Category.BigPlant ,10 ,
//            Discount() ,0
//        ))


        viewModel.currentId1.observe(viewLifecycleOwner){

//            val adapter = BuyerShopDetailsAdapter(findNavController(), context ,layoutInflater ,Repositories.ordersRepository ,it,Repositories.accountsRepository)
//            Log.d("123332123s" ,adapter.toString())
//            binding.recyclerrr.adapter =adapter
//            val layoutManager =LinearLayoutManager(context)
//            binding.recyclerrr.layoutManager =layoutManager


        }
        viewModel.plants.observe(viewLifecycleOwner){
            Log.d("123332123s",it.toString())
            adapter.plants =it.toMutableList()
            adapter.notifyDataSetChanged()
        }

        if(arguments!=null) {
            val shop = requireArguments().getSerializable("KEY") as Shop
            Log.d("1231231232132dd", shop.toString())
            binding.txtGogreennurser.text = shop.name
            binding.txtLoremipsuaVolu.text = shop.adress
            binding.txtPrice.text = shop.number
            binding.txtEmail.text = shop.email
            binding.txtPriceOne.text = "delivery fees ${shop.fee}"
            // }


        lifecycleScope.launch (Dispatchers.IO){
            viewModel.getAllPlantsOfThisShop(shop)
        }
        }


        //adapter.plants =createData()
        binding.btnGroupTwelve.setOnClickListener{
            //sort by name up/down   , sort by date  up/down
            //when pressed change it's will sort by those and write by Toast Messages about it

        }
        viewModel.plantsSortState.observe(viewLifecycleOwner){
            plantsSortState =it
        }


        binding.btnGroupTwelve.setOnClickListener {
                when (plantsSortState) {
                    "by_id" -> {
                        Toast.makeText(context, "by_name_incr", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getPlantsByCatefory_incr()
                        }
                        viewModel.plantsSortState.postValue("by_name_incr")



                    }

                    "by_name_incr" -> {
                        Toast.makeText(context, "by_name_dcr", Toast.LENGTH_SHORT).show()

                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getPlantsByName_decr()
                        }
                        viewModel.plantsSortState.postValue("by_name_dcr")



                    }

                    "by_name_dcr" -> {
                        Toast.makeText(context, "by_id", Toast.LENGTH_SHORT).show()
                        lifecycleScope.launch(Dispatchers.IO) {
                            viewModel.getAllMyPlants()
                        }
                        viewModel.plantsSortState.postValue("by_id")

                    }

                }
            }



            return binding.root
    }


}