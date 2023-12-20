package com.example.sellseeds.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.user.entities.UserDbEntity

@Dao
interface UserDao {
    @Insert
    fun createUser(user: UserDbEntity)
    @Query("Select id,password from user  where email=:email")
    suspend fun findByEmail( email:String): Tuples.UserSignData?
    @Update(entity = UserDbEntity::class)
    suspend fun updateUserData(user:User)
    @Query("Select * from user ")
    fun getAllById(): List<UserDbEntity>









}