package com.example.sellseeds.model.plants

import com.example.sellseeds.model.plants.entenies.Plant
import kotlinx.coroutines.flow.MutableStateFlow

interface PlantsRepository {

    fun addPlant(plant:Plant)

    fun deletePlant(plant:Plant)

    fun getAllPLants(){}
}