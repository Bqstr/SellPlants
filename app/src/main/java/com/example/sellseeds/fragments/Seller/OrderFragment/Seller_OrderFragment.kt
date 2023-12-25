package com.example.sellseeds.fragments.Seller.OrderFragment

import android.os.Bundle
import android.service.controls.actions.BooleanAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

import com.example.sellseeds.R
import com.example.sellseeds.ViewModelCreator
import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.databinding.ActivitySellerEditprofileOneBinding
import com.example.sellseeds.databinding.ActivitySellerOrderBinding
import com.example.sellseeds.fragments.Seller.AddProduct.ADD_PRODUCT_KEY
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.viewModelCreator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.util.Date

/**
 * A simple [Fragment] subclass.
 * Use the [Seller_OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
const val BACK_FROM_ORDER ="BACK_FROM_ORDER"
class Seller_OrderFragment : Fragment() {





lateinit var binding:ActivitySellerEditprofileOneBinding
val viewModel by viewModelCreator{Seller_OrderViewModel(Repositories.ordersRepository ,Repositories.shopRepository ,Repositories.accountsRepository)}
    lateinit var order:Orders
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivitySellerEditprofileOneBinding.inflate(layoutInflater)
        var orderId =binding.txt1087543245678



        if(arguments!=null){

            var quantity=binding.txtPrice

            val orderDetailwithUser =requireArguments().getSerializable("KEY") as OrderDetailwithUser
             order =orderDetailwithUser.order
            binding.txtPriceTwo.text =order.price.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.getPlantByOrderId(order.id)
                viewModel.getUserByOrderId(order.id)
                viewModel.getShopByOrder(order)

            }









            orderId.text =order.id.toString()

            val a =order.price
            val b= quantity.text.toString().toInt()
            Log.d("1fldvsdovndv" ,"$a $b")
            binding.txtPriceTwo.text =order.price.toString()

            binding.txtPriceOne.text =a.toString()







            if(orderDetailwithUser.isBuyer){
                binding.cancelSellerOrder.visibility =View.GONE
                binding.deliveryyy.visibility =View.GONE
            }
            else{
                binding.cancelSellerOrder.visibility =View.VISIBLE
                binding.deliveryyy.visibility =View.VISIBLE
            }
            binding.linearRowarrowleft.setOnClickListener{
                findNavController().navigateUp()
            }



           binding.txt9187543245678.text =order.buyer?.number
            binding.txtLoremipsumdol.text =order.buyer?.adress
           // binding.imagePlantOrderSeller.setImageResource(order.plant?.images!!)
            binding.txtMonsteraplants.text =order.plant?.name
            binding.txtPriceOne.text =order.plant?.price.toString()



            val dd = Date(order.date.toLong() * 1000)
            binding.txt2042022.text =dd.toString()
            when(order.status){
                OrderStatus.Canceled -> binding.txtCompleted.setTextColor(getResources().getColor(R.color.red))
                OrderStatus.InProgress -> binding.txtCompleted.setTextColor(getResources().getColor(R.color.black))
                OrderStatus.Completed -> binding.txtCompleted.setTextColor(getResources().getColor(R.color.greeeen))


            }
            binding.txtCompleted.text =Converter.OrderStatustoString(order.status)



        }
        var orderDate =binding.txt2042022
        var orderSatus =binding.txtCompleted
        var byuerEmail=binding.txtEmail
        var buyerPhone=binding.txt9187543245678
        var deliveryAdress=binding.txtLoremipsumdol


        var plantImage=binding.imagePlantOrderSeller
        var plantName=binding.txtMonsteraplants

        var plant_price=binding.txtPriceOne //considering fee , and discount
//

        binding.imagePlantOrderSeller.setImageResource(R.drawable.faux_palm_tree)
        binding.deliveryyy.setOnClickListener{
            order.status =OrderStatus.Completed
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.toDeliver(order)
            }


            findNavController().navigateUp()
        }
        binding.cancelSellerOrder.setOnClickListener{
            order.status =OrderStatus.Canceled

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.toDeliver(order)
            }

            findNavController().navigateUp()
        }

        viewModel.user.observe(viewLifecycleOwner){

            Log.d("2223212","${it.toString()}")
            binding.txtEmail.text =it.email
            binding.txt9187543245678.text =it.number
            binding.txtLoremipsumdol.text =it.adress
        }
        viewModel.shop.observe(viewLifecycleOwner){
        }
        viewModel.plant.observe(viewLifecycleOwner){

            binding.txtMonsteraplants.text =it.name
            binding.txtPriceOne.text =it.price.toString()

            //binding.imagePlantOrderSeller.setImageResource(it.images)

            MainScope().launch {


                // Update the user interface (UI) with the thread result of the user interface (UI)
                withContext(Dispatchers.Main) {
                    val resizeImage = it.images

                    Glide.with(requireContext())
                        .load(resizeImage)
                        .into(binding.imagePlantOrderSeller)
                }
            }




        }


        // Inflate the layout for this fragment
        return binding.root
    }




}
data class OrderDetailwithUser(var order: Orders ,var isBuyer:Boolean):Serializable

class ActionOrder(var orderId :Long,var toDelete:Boolean =false ,var toDeliver:Boolean =false ,var toCancel:Boolean =false ):Serializable{



}