package com.example.sellseeds.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.LAYER_TYPE_HARDWARE
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellseeds.databinding.LoadStateBinding
import java.security.KeyStore.LoadStoreParameter

class HelpAdapter: LoadStateAdapter<HelpAdapter.HolderHere>() {






    class HolderHere(val binding:LoadStateBinding):ViewHolder(binding.root){

    }

    override fun onBindViewHolder(holder: HolderHere, loadState: LoadState) {

           holder.binding.progressBar.isVisible =loadState is LoadState.Loading
           holder.binding.textforEnd.isVisible =loadState is LoadState.Loading

        holder.binding.progressBar.isVisible =loadState is LoadState.Error
        holder.binding.textforEnd.isVisible =loadState is LoadState.Error


    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): HolderHere {
        val inflater = LayoutInflater.from(parent.context)
        val binding =LoadStateBinding.inflate(inflater ,parent ,false)
        return HolderHere(binding)

    }
}