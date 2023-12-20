package com.example.sellseeds.fragments.Buyer.registerUser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.user.UserRepository

class RegisterUserViewModel(private val userRepository: UserRepository,
                            private val currentId: UserCurrentId):ViewModel() {
    val navigateToMain =MutableLiveData<Boolean>()
    val state =MutableLiveData<RegisterUserState>()



    suspend fun register(user: User) {

        userRepository.createUser(user)
        Log.d("abuuuuub",user.name)
        navigateToMain.postValue(true)


    }

}
class RegisterUserState(var name :String, val adress:String, var number:String, var email:String, var password:String)