package com.example.sellseeds.dataClass_enum

import androidx.room.Ignore
import java.io.Serializable


data class Orders(val id:Int,
                  val price:Int,
                  @Ignore
                  val plant: Seed?,
                  val amount: Int,//
                  val plant_id:Int,
                  val user_id:Int?,
                  @Ignore
                  val buyer: User?,

                  var status: OrderStatus, val date:Int, val adress:String): Serializable
