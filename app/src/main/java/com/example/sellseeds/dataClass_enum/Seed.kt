package com.example.sellseeds.dataClass_enum

import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import java.io.Serializable

data class Seed(val id:Long, val name:String, val description:String, val price:Int, val images :List<Int>, val category: Category,
                val quantity:Int,//Amount of plants
                val discount: Discount

    ):Serializable
