package com.example.sellseeds.model.user

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
    @Query("Select * from user  where email=:email")
    suspend fun findByEmail( email:String): UserDbEntity?
    @Update(entity = UserDbEntity::class)
    suspend fun updateUserData(user:User)
    @Query("Select * from user ")
    fun getAllById(): List<UserDbEntity>

    @Query("Select * from user where id =:id ")
    fun getUserById(id:Int):UserDbEntity?

    @Query("SELECT * FROM user where email =:email")
    fun getUserByEmail(email:String):UserDbEntity









}