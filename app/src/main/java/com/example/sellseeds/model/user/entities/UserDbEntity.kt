package com.example.sellseeds.model.user.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sellseeds.dataClass_enum.User

@Entity(tableName = "user")
data class UserDbEntity(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name ="email") val email:String,
    @ColumnInfo(name ="name") val name:String ,
    @ColumnInfo(name ="adress") val adress:String,
    @ColumnInfo(name ="password") val password:String,
    @ColumnInfo(name ="number") val number:String




){

    fun toUser():User = User(
        id =id,
        email =email,
        name =name,
        adress=adress,
        number = number,
        password = password,
        orders = mutableListOf()
        )

    companion object{
        fun fromUserSignUpData(signUpData: User): UserDbEntity = UserDbEntity(
                id =0,
                email =signUpData.email,
                name =signUpData.name,
                adress=signUpData.adress,
            number =signUpData.number,
            password = signUpData.password
        )
        }
    }
