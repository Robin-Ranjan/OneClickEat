package com.rajeev.fooddelivery.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rajeev.fooddelivery.NotificationBottomSheet
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val NavController:NavController    = findNavController(R.id.fragmentContainerView)
        val bottomNav : BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setupWithNavController(NavController)
        binding.notificationIcon.setOnClickListener{
            val bottomSheetDialog = NotificationBottomSheet()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }


    }
}