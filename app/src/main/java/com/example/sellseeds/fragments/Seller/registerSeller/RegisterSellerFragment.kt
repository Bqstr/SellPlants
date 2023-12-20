package com.example.sellseeds.fragments.Seller.registerSeller

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Rating
import com.example.sellseeds.dataClass_enum.Shop
import com.example.sellseeds.databinding.ActivityRegisterSellerBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterSellerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterSellerFragment : Fragment() {


    val viewModel by viewModelCreator { RegisterSellerViewModel(Repositories.shopRepository,Repositories.shopCurrentId ) }
lateinit var binding:ActivityRegisterSellerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivityRegisterSellerBinding.inflate(layoutInflater)


        viewModel.naviagate_toMain.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_registerSellerFragment_to_sellerHomePageFragment)

        }
        binding.toleBar.imageArrowleft.setOnClickListener{
            //TO DO go back
            findNavController().popBackStack()
        }

        val shopImages = mutableListOf<Int>()
        shopImages.add(R.drawable.img_rectangle19)
        shopImages.add(R.drawable.img_rectangle19)
        shopImages.add(R.drawable.img_rectangle2)



        binding.btnRegister.setOnClickListener{
            val name =binding.etGroupTwentyTwo.text.toString()
            val email =binding.etGroupFive.text.toString()
            val number =binding.etGroupTwelve.text.toString()
            val fee =binding.etGroupTwentyThree.text.toString().toInt()
            val adress =binding.etGroupThirteen.text.toString()
            val password =binding.etGroupSix.text.toString()

            val shop = Shop(0,name =name ,email =email ,password =password ,number =number , adress =adress , fee = fee , image = shopImages.random() , products = mutableListOf() , orders = mutableListOf() ,
                rating =Rating.FOUR_STAR )
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.registerShop(shop)
                viewModel.naviagate_toMain.postValue(true)

            }


            //findNavController().navigate(R.id.action_registerSellerFragment_to_sellerHomePageFragment)
        }



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
         * @return A new instance of fragment RegisterSellerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterSellerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}