package com.example.sellseeds.model.user

import android.util.Log
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.user.entities.UserDbEntity

class RoomUserRepository(val dao: UserDao, val userCurrentId: UserCurrentId) : UserRepository {

//    private val currentAccountIdFlow = AsyncLoader {
//        MutableStateFlow(UserID(appSettings.getCurrentAccountId()))
//    }
//    override val getAllData: LiveData<List<UserDbEntity>>
//        get() = dao.getAllById()




    override suspend fun createUser(user: User) {
        dao.createUser(UserDbEntity.fromUserSignUpData(user))
        userCurrentId.setCurrentUserId(dao.getUserByEmail(user.email)!!.id)

        //Log.d("abioooobs","123123123123")
    }

    override suspend fun signIn(email: String, password: String):Boolean {
        val us = dao.findByEmail(email) ?: return false
        Log.d("dhhdhdhdhd","abobus")
        Log.d("dhhdhdhdhd","${password}   ${us.password}    ${us.id}   ${us.email}  ${us.adress}")

        if(us.password==password){
            Log.d("asasasasasas",us.id.toString())
            userCurrentId.setCurrentUserId(us.id)
            Log.d("asasasasasas", userCurrentId.getCurrentUserId().toString())
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

    override suspend fun getUserById(id: Int): User {
        val us =dao.getUserById(id)
        if(us==null){
            Log.d("2qwewq", dao.getUserById(id).toString())

            return dao.getUserById(id)!!.toUser()

        }
        else{
            return dao.getUserById(id)!!.toUser()
        }

    }

    class UserID(val value:Int)
}