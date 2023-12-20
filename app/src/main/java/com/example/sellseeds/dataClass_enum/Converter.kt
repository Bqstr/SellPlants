package com.example.sellseeds.dataClass_enum

class Converter {

    companion object {
        fun CategorytoString(category: Category): String {
            return when (category) {
                Category.SmallPlant -> {
                    "Small Plant"
                }

                Category.BigPlant -> "Big Plant"
                Category.MediumPlant -> "Medium Plant"

            }

        }

        fun CategoryfromString(s: String): Category {
            return when (s) {
                "Small Plant" -> Category.SmallPlant
                "Medium Plant" -> Category.MediumPlant
                "Big Plant" -> Category.BigPlant
                else -> {
                    Exception()
                    Category.SmallPlant
                }
            }
        }

        fun RatingfromString(s:String):Rating{

            return when (s.toLowerCase()) {
                "1" -> Rating.ONE_STAR
                "2" -> Rating.TWO_STAR
                "3" -> Rating.THREE_STAR
                "4" -> Rating.FOUR_STAR
                "5" -> Rating.FIVE_STAR
                else -> throw IllegalArgumentException("Invalid rating string")
            }
        } //rating is from 0 up to 5 stars

        fun RatingtoString(rating:Rating):String{
            return when (rating) {
                Rating.ONE_STAR -> "1"
                Rating.TWO_STAR -> "2"
                Rating.THREE_STAR -> "3"
                Rating.FOUR_STAR -> "4"
                Rating.FIVE_STAR -> "5"

            }
        }//rating is from 0 up to 5 stars

        fun OrderStatustoString(orderStatus: OrderStatus):String{
            return when (orderStatus) {
                OrderStatus.Completed -> "Completed"
                OrderStatus.InProgress -> "InProgress"
                OrderStatus.Canceled -> "Canceled"
            }

        }//Completed, InProgress, Canceled

        fun OrderStatisfromString(s:String):OrderStatus{
            return when (s.toLowerCase()) {
                "completed" -> OrderStatus.Completed
                "inprogress" -> OrderStatus.InProgress
                "canceled" -> OrderStatus.Canceled
                else -> throw IllegalArgumentException("Invalid order status string")
            }
        }//Completed, InProgress, Canceled


    }
}