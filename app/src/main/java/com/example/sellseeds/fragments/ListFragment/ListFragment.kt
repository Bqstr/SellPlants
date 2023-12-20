package com.example.sellseeds.fragments.ListFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.example.sellseeds.DeleteLater

import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.SeedsAdapter
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment(R.layout.fragment_list) {
    // TODO: Rename and change types of parameters

    lateinit var binding : FragmentListBinding
    val veiwModel by viewModels<ListViewModel>()


//take photos from this site !!!!!!!!!!!!!!!!!https://www.thesill.com/!!!!!!!!!!!!!!!!!!!
    //тут я понял что более логичным названием будет Plants а не Seeds



    lateinit var adapter: SeedsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentListBinding.bind(view)
//        adapter = SeedsAdapter()
//
//        val layoutManager = GridLayoutManager(context, 2)
//
//
//        binding.recyclerView.adapter =adapter
//        binding.recyclerView.layoutManager =layoutManager
//
//
//        adapter.seeds =createData()


    }



    private fun createData(): MutableList<Seed> {
        val mutableList = mutableListOf<Seed>(
            Seed(0, "Olive Tree", "description", 1000, listOf(R.drawable.img_rectangle12), Category.SmallPlant ,1, Discount(true, 0.2)),
            Seed(1, "Money Tree", "description", 2000, listOf(R.drawable.img_rectangle12_1), Category.SmallPlant ,1, Discount(true, 0.2)),
            Seed(2, "Faux Palm Tree", "description", 3000, listOf(R.drawable.img_rectangle12_108x110), Category.SmallPlant ,1, Discount(true, 0.2)),
            Seed(3, "Kek Tree", "description", 999, listOf(R.drawable.img_rectangle12_2), Category.SmallPlant ,1, Discount(true, 0.2)),
            Seed(0, "Olive Tree", "description", 1000, listOf(R.drawable.img_rectangle12), Category.SmallPlant ,1, Discount(true, 0.2)),
            Seed(2, "Faux Palm Tree", "description", 3000, listOf(R.drawable.img_rectangle12_108x110), Category.SmallPlant ,1, Discount(true, 0.2)),


            )
        return mutableList

    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeleteLater().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }





    }
