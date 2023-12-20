package com.example.sellseeds.fragments.Seller.OrderFragment

import android.os.Bundle
import android.service.controls.actions.BooleanAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.databinding.ActivitySellerEditprofileOneBinding
import com.example.sellseeds.databinding.ActivitySellerOrderBinding
import com.example.sellseeds.fragments.Seller.AddProduct.ADD_PRODUCT_KEY
import java.io.Serializable

/**
 * A simple [Fragment] subclass.
 * Use the [Seller_OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
const val BACK_FROM_ORDER ="BACK_FROM_ORDER"
class Seller_OrderFragment : Fragment() {





lateinit var binding:ActivitySellerEditprofileOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =ActivitySellerEditprofileOneBinding.inflate(layoutInflater)
        var orderId =binding.txt1087543245678


        if(arguments!=null){

            var quantity=binding.txtPrice

            val orderDetailwithUser =requireArguments().getSerializable("KEY") as OrderDetailwithUser
            var order =orderDetailwithUser.order
            orderId.text =order.oderId.toString()

            val a =order.price
            val b= quantity.text.toString().toInt()
            Log.d("1fldvsdovndv" ,"$a $b")
            binding.txtPriceTwo.text ="total:${a*b}"

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


            binding.txtEmail.text =order.buyer.email
           binding.txt9187543245678.text =order.buyer.number
            binding.txtLoremipsumdol.text =order.buyer.adress
            binding.imagePlantOrderSeller.setImageResource(order.plant.images)
            binding.txtMonsteraplants.text =order.plant.name
            binding.txtPriceOne.text =order.plant.price.toString()

            binding.txt2042022.text =order.date.toString()
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
            var arg =Bundle()
            arg.putSerializable(BACK_FROM_ORDER ,ActionOrder(orderId.text.toString().toLong(), toDeliver = true))

            setFragmentResult("KEY", arg)

            findNavController().navigateUp()
        }
        binding.cancelSellerOrder.setOnClickListener{
            var arg =Bundle()
            arg.putSerializable(BACK_FROM_ORDER ,ActionOrder(orderId.text.toString().toLong(), toCancel = true))

            setFragmentResult("KEY", arg)

            findNavController().navigateUp()
        }


        // Inflate the layout for this fragment
        return binding.root
    }




}
data class OrderDetailwithUser(var order: Orders ,var isBuyer:Boolean):Serializable

class ActionOrder(var orderId :Long,var toDelete:Boolean =false ,var toDeliver:Boolean =false ,var toCancel:Boolean =false ):Serializable{



}