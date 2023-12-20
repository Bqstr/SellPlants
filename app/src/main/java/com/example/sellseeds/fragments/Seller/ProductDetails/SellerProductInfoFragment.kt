package com.example.sellseeds.fragments.Seller.ProductDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.databinding.FragmentSellerProductInfoBinding
import com.example.sellseeds.databinding.RowGridrectangletwelveTwoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SellerProductInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SellerProductInfoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    lateinit var binding: FragmentSellerProductInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentSellerProductInfoBinding.inflate(layoutInflater)

        val plant =arguments?.getSerializable("12321321") as Seed

        binding.textView3.text =plant.name
        binding.abobus.setImageResource(plant.images)

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SellerProductInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SellerProductInfoFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}