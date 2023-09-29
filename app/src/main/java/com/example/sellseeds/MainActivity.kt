package com.example.sellseeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sellseeds.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

//Login screen
interface Navigation{
    fun toLogInPage()

    fun toListPage()
}
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)











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
        val fragment = ListFragment.newInstance(this "123")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_conteiner, fragment)
            .addToBackStack(null)
            .commit()
    }


    fun toLogInPage() {
        val fragment = LogIn_Fragment.newInstance("123", "123")
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_conteiner, fragment)
            .addToBackStack(null)
            .commit()
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