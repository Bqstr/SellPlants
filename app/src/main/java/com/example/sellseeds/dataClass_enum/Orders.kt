package com.example.sellseeds.dataClass_enum

import java.io.Serializable


data class Orders(val oderId:Int,
                  val price:Int,
                  val plant: Seed,
                  val amount: Int,//
                  val buyer: User,
                  val shop : Shop,
                  val status: OrderStatus, val date:Int, val adress:String): Serializable
