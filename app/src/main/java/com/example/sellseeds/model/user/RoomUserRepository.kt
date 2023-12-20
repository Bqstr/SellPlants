package com.example.sellseeds.model.user

import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.fragments.Buyer.registerUser.UserCurrentId
import com.example.sellseeds.model.UserDao
import com.example.sellseeds.model.user.entities.UserDbEntity

class RoomUserRepository(val dao: UserDao, val userCurrentId:UserCurrentId) : UserRepository {

//    private val currentAccountIdFlow = AsyncLoader {
//        MutableStateFlow(UserID(appSettings.getCurrentAccountId()))
//    }
//    override val getAllData: LiveData<List<UserDbEntity>>
//        get() = dao.getAllById()




    override suspend fun createUser(user: User) {
        userCurrentId.setCurrentUserId(user.id)
        dao.createUser(UserDbEntity.fromUserSignUpData(user))
        //Log.d("abioooobs","123123123123")
    }

    override suspend fun signIn(email: String, password: String):Boolean {
        val us = dao.findByEmail(email) ?: return false
        if(us.password==password){
            userCurrentId.setCurrentUserId(us.id)
            return true

        }
        return false

    }
//        val t =dao.findByEmail(email)
//        if(t==null || t.password!=password){}
//            else{
//            }
//    }

    override suspend fun getUser(): List<User>{
       return dao.getAllById().map { UserDbEntity -> UserDbEntity.toUser() }






        }
    class UserID(val value:Int)
}