package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.MainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.historyFragment,
                R.id.listFragment,
                R.id.analyticsFragment
            )
        )
        bottomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.itemIconTintList = null



//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val currencies = DependencyInjection.repository.getCurrencies()
//                Log.d("MY_TAG", "$currencies")
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.d("MY_TAG", e.localizedMessage)
//            }
//        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}