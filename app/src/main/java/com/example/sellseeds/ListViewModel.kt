package com.example.sellseeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel(val importedSeed :List<Seed>):ViewModel() {

    private val seeds = MutableLiveData<List<Seed>>();
    val _seeds :LiveData<List<Seed>> =seeds

    init{
        seeds.value =importedSeed
    }






}