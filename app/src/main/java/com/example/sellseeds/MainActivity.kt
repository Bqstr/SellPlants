package com.example.sellseeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController

import com.example.sellseeds.databinding.ActivityMainBinding
import com.example.sellseeds.fragments.ListFragment.ListFragment
import com.example.sellseeds.model.Repositories
import com.google.android.material.bottomnavigation.BottomNavigationView

//Login screen
interface Navigation{
    fun toLogInPage()

    fun toListPage()
}
class MainActivity : AppCompatActivity() {


    private lateinit var navController :NavController

    lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //installSplashScreen()

        super.onCreate(savedInstanceState)
        Repositories.init(applicationContext)

        binding = ActivityMainBinding.inflate(layoutInflater)


        //window.statusBarColor = ContextCompat.getColor(this, R.color.greeeen)

setContentView(binding.root)



        val navHostFragment =supportFragmentManager.findFragmentById(R.id.fragment_conteiner) as NavHostFragment
        navController =navHostFragment.navController
        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView,navController)

window.decorView.setBackgroundColor(   getResources().getColor(R.color.white))


//        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.userAccount_ic ->{
//                   // findNavController(R.id.fragment_conteiner).navigate(R.id.action_listFragment_to_userAccount)
//
//
//                }
//                R.id.main_page_ic ->{
//
//                }
//            }
//            true
//        }




//        binding.logInLogInPage.setOnClickListener {
//            val fragment = ListFragment.newInstance("UUUU", "123123213")
//            binding.fragmentContainerView.visibility =View.VISIBLE
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragmentContainerView, fragment)
//                .commit()
//        }

    }





    fun toListPage() {
        
//        val fragment = ListFragment.newInstance("123","123")
//        supportFragmentManager
//            .beginTransaction()
//
//            .replace(R.id.fragment_conteiner, fragment)
//            .addToBackStack(null)
//            .commit()
    }


    fun toLogInPage() {
//        val fragment = LogIn_Fragment.newInstance("123", "123")
//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragment_conteiner, fragment)
//            .addToBackStack(null)
//            .commit()
    }
























    //ActionBar
    //добавить меню в ActionBar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Обрабатовать нажтия на элементы меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh ->{

            }
        }
        return super.onOptionsItemSelected(item)
    }
}