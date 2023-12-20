package com.example.sellseeds.fragments.LogInFramgnet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sellseeds.dataClass_enum.User
import com.example.sellseeds.model.user.UserCurrentId
import com.example.sellseeds.model.shop.ShopRepository
import com.example.sellseeds.model.user.entities.UserDbEntity
import com.example.sellseeds.model.user.UserRepository

class SignInViewModel(
    private val userRepository: UserRepository,
    private val currentId: UserCurrentId,
    private val shopRepository: ShopRepository

    ):ViewModel() {

val navigateToMain = MutableLiveData<Boolean>()
    val userData= MutableLiveData<List<UserDbEntity>>()

    //val getAllData:LiveData<List<UserDbEntity>>





     suspend fun getData(): List<User>?{
        if(userRepository.getUser()==null || userRepository.getUser()!!.isEmpty() ){
            Log.d("aaaasssaaa","pidec")
        }
         else{
            Log.d("aaaasssaaa",userRepository.getUser()!![0].name)

         }

         return userRepository.getUser()


     }


     suspend fun register(user:User) {
         //viewModelScope.launch {
         //Log.d("12333333333333333", "123123123")
         userRepository.createUser(user)
         Log.d("abuuuuub",user.name)
         navigateToMain.postValue(true)
        // Log.d("12333333333333333", "123123123")

     }

    suspend fun signInUser(email:String, password:String){
        if(userRepository.signIn(email ,password)  ){

            navigateToMain.postValue(true)

        }
        else{
            Log.d("dhhdhdhdhd","wrong")

        }

    }
    suspend fun signInShop(email:String, password:String){
        if(shopRepository.signInShop(email ,password) ){
            navigateToMain.postValue(false)
        }

    }
       // }




}