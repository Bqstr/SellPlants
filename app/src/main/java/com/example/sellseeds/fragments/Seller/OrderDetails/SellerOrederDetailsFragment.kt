package com.example.sellseeds.fragments.Seller.OrderDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.databinding.ActivitySellerEditprofileOneBinding
import com.example.sellseeds.databinding.ActivityUserOrderdetailBinding
import com.example.sellseeds.fragments.Seller.HomePage.GO_ORDER_DETAILS

/**
 * A simple [Fragment] subclass.
 * Use the [SellerOrederDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SellerOrederDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding:ActivitySellerEditprofileOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivitySellerEditprofileOneBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        if(arguments!=null){
            requireArguments().getSerializable(GO_ORDER_DETAILS) as Orders


        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SellerOrederDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SellerOrederDetailsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}