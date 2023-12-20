package com.example.sellseeds.dataClass_enum

import androidx.room.Ignore
import com.example.sellseeds.dataClass_enum.Rating
import java.io.Serializable

data class Shop(val id :Int, val name:String, val email:String, val adress:String, val number:String,
                @Ignore
                val products:MutableList<Seed>,
                @Ignore
                val orders:MutableList<Orders>?,
                val image:Int,


                @Ignore
                val rating: Rating, val fee:Int, val password:String ):Serializable
