package com.example.sellseeds.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.OrderStatus
import com.example.sellseeds.dataClass_enum.Orders
import com.example.sellseeds.databinding.OrderItemBinding
import com.example.sellseeds.fragments.Seller.OrderFragment.OrderDetailwithUser
import com.example.sellseeds.model.Repositories
import com.example.sellseeds.model.orders.OrdersRepository
import com.example.sellseeds.model.shop.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class OrdersAdapter (val navConteoller: NavController, val context: Context, var isBuyer:Boolean ,  ): RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>(){
    val ordersRepository =Repositories.ordersRepository
    //val shopRepository =Repositories.shopRepository
    var orders = mutableListOf<Orders>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {

        val order =orders[position]

        MainScope().launch {


            // Update the user interface (UI) with the thread result of the user interface (UI)
            withContext(Dispatchers.Main) {
              val user=ordersRepository.getUserByOrderId(order.id)
                holder.binding.orderEmail.text =user.email
            }
        }

            with(holder.binding){
                //txtDate.text =order.date    //Convert it into date
                val dd = Date(order.date.toLong() * 1000)

                txtDate.text =dd.toString()
                oderId.text =order.id.toString()

              //  val shop = ordersRepository.getShopByOrderId(order.id)

                //orderEmail.text =

                txtPrice.text =order.price.toString()





                when(order.status){
                    OrderStatus.Completed ->{txtCompleted.setTextColor(ContextCompat.getColor(context!!,
                        R.color.greeeen
                    ))}
                    OrderStatus.Canceled ->{txtCompleted.setTextColor(ContextCompat.getColor(context!!,
                        R.color.red
                    ))}
                    OrderStatus.InProgress->{txtCompleted.setTextColor(ContextCompat.getColor(context!!,
                        R.color.grey
                    ))}

                }
                txtCompleted.text =order.status.name

            }

        holder.binding.root.setOnClickListener{
            val arg =Bundle()

            if(isBuyer){
                arg.putSerializable("KEY", OrderDetailwithUser(order, true))

                navConteoller.navigate(R.id.action_buyer_HomePageFragment_to_seller_OrderFragment, arg)

            }else{
                arg.putSerializable("KEY", OrderDetailwithUser(order, false))

                navConteoller.navigate(R.id.action_sellerHomePageFragment_to_seller_OrderFragment, arg)

            }
        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =OrderItemBinding.inflate(inflater,parent,false)
        return OrderViewHolder(binding)
    }


    override fun getItemCount(): Int =orders.size



    class OrderViewHolder(val binding: OrderItemBinding):RecyclerView.ViewHolder(binding.root){

    }



    }
