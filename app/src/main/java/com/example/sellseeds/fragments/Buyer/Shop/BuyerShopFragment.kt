package com.example.sellseeds.fragments.Buyer.Shop

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.helper.widget.Carousel.Adapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.R
import com.example.sellseeds.adapters.BuyerShopDetailsAdapter
import com.example.sellseeds.adapters.HelpAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.dataClass_enum.Shop

import com.example.sellseeds.databinding.ActivityShopBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [BuyerShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuyerShopFragment : Fragment() {
    var plantsSortState ="by_id"
lateinit var adapter:BuyerShopDetailsAdapter
lateinit var helpAdapter:HelpAdapter
    private lateinit var mainLoadStateHolder: HelpAdapter.HolderHere

lateinit var binding:ActivityShopBinding
val viewModel by viewModelCreator{ BuyerShopViewModel(Repositories.userCurrentId ,Repositories.plantsRepository ,Repositories.shopRepository) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityShopBinding.inflate(layoutInflater)


         adapter = BuyerShopDetailsAdapter(findNavController(), context ,layoutInflater ,Repositories.ordersRepository ,Repositories.userCurrentId,Repositories.accountsRepository)
        val helpAdapter =HelpAdapter()
        val adapterwithelp =adapter.withLoadStateFooter(helpAdapter)
        binding.recyclerrr.adapter =adapter   //adapterwithelp
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
//        viewModel.plants.observe(viewLifecycleOwner){
//            Log.d("123332123s",it.toString())
//            adapter.plants =it.toMutableList()
//            adapter.notifyDataSetChanged()
//        }

        if(arguments!=null) {
            val shop = requireArguments().getSerializable("KEY") as Shop
            Log.d("1231231232132dd", shop.toString())
            binding.txtGogreennurser.text = shop.name
            binding.txtLoremipsuaVolu.text = shop.adress
            binding.txtPrice.text = shop.number
            binding.txtEmail.text = shop.email
            binding.txtPriceOne.text = "delivery fees ${shop.fee}"
            //

        //viewModel.currentShopId.postValue(shop.id)
            submitPlants(shop.id)
//
        }
        viewModel.submitToAdapter.observe(viewLifecycleOwner){
            if(it) {
                lifecycleScope.launch {
                    viewModel.plantFlow.collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
                }
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
                            //viewModel.getPlantsByShopId_incr()
                        }
                    }
                    else if(plantsSortState=="by_name_incr"){
                        lifecycleScope.launch(Dispatchers.IO) {
                           // viewModel.getPlantsByShopId_decr()
                        }
                    }
                    else{
                        lifecycleScope.launch(Dispatchers.IO) {
                           // viewModel.getAllMyPlants()
                        }
                    }

                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })




            return binding.root
    }
    fun submitPlants(shopId:Int){
        lifecycleScope.launch {
            viewModel.commitPlants(shopId)

        }

    }

    fun onChange(txt: String?) {
        val plantList =viewModel.plants.value
        val newList = mutableListOf<Seed>()
        if(txt!=null && plantList!=null){
            for(a in 0 until plantList.size){
                if(plantList.get(a).name.contains(txt)){
                    newList.add(plantList.get(a))
                }
            }
            viewModel.plants.postValue(newList)



        }

    }


}