package com.example.sellseeds.fragments.Seller.AddProduct

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.Discount
import com.example.sellseeds.dataClass_enum.Seed
import com.example.sellseeds.databinding.ActivityAddproductBinding
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [AddProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
const val ADD_PRODUCT_KEY ="ADD_PRODUCT_KEY"

class AddProductFragment : Fragment() {

lateinit var binding:ActivityAddproductBinding
val viewModel by viewModelCreator { AddProductViewModel(
    Repositories.plantsRepository ,Repositories.shopCurrentId ,Repositories.shopRepository)
}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding =ActivityAddproductBinding.inflate(layoutInflater)

        val spinner = binding.etGroupThirteen
        var category =Category.BigPlant
        ArrayAdapter.createFromResource(requireContext() ,R.array.category_array ,R.layout.dropdown_categories).also { adapter ->
            // Specify the layout to use when the list of choices appears.
           // adapter.setDropDownViewResource(R.layout.dropdown_categories)
            // Apply the adapter to the spinner.
            binding.etGroupThirteen.adapter = adapter
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                category =Converter.CategoryfromString(parent!!.getItemAtPosition(position).toString())

            }

        }


        viewModel.currentId.observe(viewLifecycleOwner){

            val title = binding.etGroupEleven.text.toString()
            val descr = binding.etGroupTwelve.text.toString()
//
            val count = binding.etGroupFive.text.toString().toInt()
            val price = binding.etGroupThirtySeven.text.toString().toInt()
            var discount = Discount()
            if (binding.dicountSwithAppProduct.isChecked) {
                discount = Discount(
                    binding.dicountSwithAppProduct.isChecked,
                    binding.etOffer.text.toString().toDouble()
                )

            }

            val product = Seed(
                0,
                title,
                descr,
                category = category,
                quantity = count,
                price = price,
                discount = discount,
                images = R.drawable.faux_palm_tree,
                shop_id =it
            )

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.addProduct(product)
            }


            Log.d("122323234123" ,it.toString())
            Log.d("122323234123" ,product.toString())






            findNavController().navigateUp()
        }
        binding.btnAddProductOne.setOnClickListener{

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getCurrentShop_id()
            }
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
         * @return A new instance of fragment AddProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddProductFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }






}