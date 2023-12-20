package com.example.sellseeds.fragments.ListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.sellseeds.dataClass_enum.Seed

class ListViewModel(val importedSeed :List<Seed>, val navigationController:NavController):ViewModel() {

    private val seeds = MutableLiveData<List<Seed>>();
    val _seeds :LiveData<List<Seed>> =seeds

    init{
        seeds.value =importedSeed
    }









}