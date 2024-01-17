package com.example.sellseeds.adapters



import androidx.recyclerview.widget.DiffUtil
import com.example.sellseeds.dataClass_enum.Seed

class DiffUtilOdin(

): DiffUtil.ItemCallback<Seed>() {




    override fun areItemsTheSame(oldItem: Seed, newItem: Seed): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Seed, newItem: Seed): Boolean {
        return oldItem==newItem

    }
}