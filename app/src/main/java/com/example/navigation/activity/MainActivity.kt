package com.example.navigation.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.navigation.R
import com.google.android.material.R.id.design_navigation_view
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_graph)

        if (navController.currentDestination?.id == R.id.splashScreen) {
            // If the current destination is the SplashScreen, exit the app
            finish()
        } else {
            // Otherwise, perform the default back button behavior (pop the back stack)
            super.onBackPressed()
        }
    }

}