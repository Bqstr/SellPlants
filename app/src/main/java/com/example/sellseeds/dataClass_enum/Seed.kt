package com.example.sellseeds.dataClass_enum

import androidx.room.Ignore
import com.example.sellseeds.dataClass_enum.Category
import com.example.sellseeds.dataClass_enum.Discount
import java.io.Serializable

data class Seed(val id:Long, val name:String, val description:String, val price:Int,
                val images :String,
                @Ignore
                val category: Category,
                val quantity:Int,//Amount of plants
                @Ignore
                val discount: Discount,
    val shop_id :Int

    ):Serializable
