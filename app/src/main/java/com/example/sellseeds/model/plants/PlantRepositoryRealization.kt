package com.example.sellseeds.model.plants

import com.example.sellseeds.model.plants.entenies.Plant
import kotlinx.coroutines.flow.MutableStateFlow

class PlantRepositoryRealization:PlantsRepository {
    private var list = MutableStateFlow<List<Plant>?>(null)
    override fun addPlant(plant: Plant) {
        TODO("Not yet implemented")
    }

    override fun deletePlant(plant: Plant) {
        TODO("Not yet implemented")
    }


}