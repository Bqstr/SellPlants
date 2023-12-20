package com.example.sellseeds.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sellseeds.R
import com.example.sellseeds.dataClass_enum.Converter
import com.example.sellseeds.dataClass_enum.Shop

import com.example.sellseeds.databinding.RowHomepageBinding

class ShopsAdapter (val navConteoller: NavController, val context: Context?): RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder>(){

    var shops = mutableListOf<Shop>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {

        val shop =shops[position]

        with(holder.binding){
            //txtDate.text =order.date    //Convert it into date
            imageRectangleNineteen.setImageResource(shop.image)
            txtLoremshop.text =shop.name
            txtFortyFive.text =Converter.RatingtoString(shop.rating)
            txt46Loremipsum.text =shop.adress


        }
        holder.binding.root.setOnClickListener{
            val arg = Bundle()
            arg.putSerializable("KEY", shop)
            navConteoller.navigate(R.id.action_buyer_HomePageFragment_to_buyerShopFragment, arg)
        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowHomepageBinding.inflate(inflater,parent,false)
        return ShopsViewHolder(binding)
    }




    override fun getItemCount(): Int =shops.size



    class ShopsViewHolder(val binding: RowHomepageBinding): RecyclerView.ViewHolder(binding.root){

    }



}