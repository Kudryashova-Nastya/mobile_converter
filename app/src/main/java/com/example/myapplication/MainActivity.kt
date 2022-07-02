package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.data.RoomInitRepository
import com.example.myapplication.databinding.MainActivityBinding
import com.example.myapplication.fragments.history.HistoryViewModel
import com.example.myapplication.fragments.history.HistoryViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory(RoomInitRepository.getRepository(this))
    }

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

    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.fragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}