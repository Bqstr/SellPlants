package com.example.sellseeds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.d("HEYYYYYYYY","12321321312312")

        val intent = Intent(this, MainActivity::class.java)
        //intent.putExtra(KEY_LOG_IN, UserLogIn(binding.email.text.toString(), binding.password.text.toString()))
        startActivity( intent)
    }

    private fun renderAnimations() {

    }
}