package com.example.sellseeds

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class BaseActivity: AppCompatActivity() {



     //lateinit var viewModel: MainViewModel
     //lateinit var binding: ActivityBaseBinding
    init {

    }
//    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        super.onCreate(savedInstanceState, persistentState)
//        Log.d("23123","sfdbdsfhsdfj")
//        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//
//        //val intent = Intent(this, LogInActivity::class.java)
//        //startActivity( intent)
//        ret
//
//
//
//    }






    fun checkUser(name:String, password:String):Boolean{

        //return viewModel.checkUser(name, password)
        return true
    }
}