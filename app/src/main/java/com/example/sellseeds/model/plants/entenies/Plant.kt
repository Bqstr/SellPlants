package com.example.sellseeds.model.plants.entenies

import java.io.Serializable

data class Plant(val id:Long ,val name:String ,val description:String,val price:Int, val images :List<Int>)
