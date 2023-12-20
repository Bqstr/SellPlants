package com.example.sellseeds.fragments.Buyer.Shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sellseeds.BuyerShopDetailsAdapter
import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.Seed

import com.example.sellseeds.databinding.ActivityShopBinding
import com.example.sellseeds.fragments.Seller.HomePage.SellerHomePageFragment

/**
 * A simple [Fragment] subclass.
 * Use the [BuyerShopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuyerShopFragment : Fragment() {

lateinit var binding:ActivityShopBinding
lateinit var adapter: BuyerShopDetailsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityShopBinding.inflate(layoutInflater)
        adapter =BuyerShopDetailsAdapter(findNavController(), context ,layoutInflater)
        val layoutManager =LinearLayoutManager(context)
        binding.recyclerrr.adapter =adapter
        binding.recyclerrr.layoutManager =layoutManager
        //adapter.plants =createData()
        binding.btnGroupTwelve.setOnClickListener{
            //sort by name up/down   , sort by date  up/down
            //when pressed change it's will sort by those and write by Toast Messages about it

        }
        // Inflate the layout for this fragment
            return binding.root
    }

//
//    private fun createData(): MutableList<Seed> {
//        val mutableList = mutableListOf<Seed>(
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

}