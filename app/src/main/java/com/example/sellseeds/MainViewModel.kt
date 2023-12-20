package com.example.sellseeds

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.User

class MainViewModel: ViewModel() {

    private val _usersList =MutableLiveData<MutableList<User>>()
    val usersList :LiveData<MutableList<User>> =_usersList

    fun checkUser(name:String, password:String):Boolean{
//        val us =usersList.value?.firstOrNull { it.name == name && it.password == password }
//        if(us==null) {
//            return false
//        }

    return true
    }


    fun addNewUser(user: User){
        val tt : MutableList<User> =_usersList.value!!
        if (tt != null) {
            tt.add(user)
        }
        else{
            println("aaaaaaaaaaa")
        }
        _usersList.postValue(tt)
    }
}