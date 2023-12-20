package com.example.sellseeds.model.user.entities

data class UserSignUpData(
     val email:String,val name:String,val adress:String ,val number :String ,val password:String,val confirmPassword:String
)
{
    fun validate(){
        if(email.isBlank()) throw Exception()
        if(name.isBlank()) throw Exception()
        if(password.isBlank()) throw Exception()


    }
}