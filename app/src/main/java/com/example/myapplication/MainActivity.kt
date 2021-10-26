package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateView = findViewById<TextView>(R.id.dateTextView)
        dateView.text = getMonth()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        val chartFrag = ChartsFragment()
        val infoData = info_data()

        val botNav = findViewById<BottomNavigationView>(R.id.botNavigation)
        botNav.setupWithNavController(navController)

    }

    fun getMonth():String{
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MMM yyyy")
        val date = simpleDateFormat.format(calendar.time)
        return date
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
