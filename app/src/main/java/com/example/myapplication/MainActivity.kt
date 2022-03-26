package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val currencies = DependencyInjection.repository.getCurrencies()
            Log.d("MY_TAG", "$currencies")
            } catch (e: Exception){
                e.printStackTrace()
                Log.d("MY_TAG", e.localizedMessage)
            }
        }
    }
}