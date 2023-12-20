package com.example.sellseeds.model.user

import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.user.entities.UserDbEntity

interface UserRepository {
    //suspend val getAllData:LiveData<List<UserDbEntity>>
    suspend fun createUser(user: User)
    suspend fun signIn(email:String, password:String):Boolean
    suspend fun getUser(): List<User>?



}