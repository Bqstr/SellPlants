package com.example.sellseeds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sellseeds.databinding.FragmentListBinding
import com.example.sellseeds.databinding.FragmentLogInBinding
import com.example.sellseeds.databinding.ListItemBinding

class SeedsAdapter: RecyclerView.Adapter<SeedsAdapter.SeedsViewHolder>() {
    var seeds = mutableListOf<Seed>()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }



//когда recyclerView создает новый элемент
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeedsViewHolder {
        val inflater =LayoutInflater.from(parent.context)
    val binding =ListItemBinding.inflate(inflater,parent,false)
    return SeedsViewHolder(binding)
    }




    override fun getItemCount()= seeds.size





        //когда recyclerView хочет обновить существующий элемиент
    override fun onBindViewHolder(holder: SeedsViewHolder, position: Int) {
        val plant =seeds[position]
            with(holder.binding) {
                seedImage.setImageResource(plant.images[0])
                plantName.text =plant.name
                price.text =plant.price.toString()
            }

    }





    class SeedsViewHolder(val binding:ListItemBinding):RecyclerView.ViewHolder(binding.root){

    }
}