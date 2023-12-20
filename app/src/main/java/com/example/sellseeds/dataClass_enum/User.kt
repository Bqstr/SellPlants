package com.example.sellseeds.dataClass_enum

import androidx.room.Ignore
import com.example.sellseeds.dataClass_enum.Orders

data class User(val id :Int, val name:String, val email:String, val adress:String, val number:String,

                @Ignore
                val orders:MutableList<Orders> , val password:String){




}
