package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.data.CurrencyApi
import com.example.myapplication.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://data.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val service = retrofit.create(CurrencyApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val currencies = service.getCurrency()
            Log.d("MY_TAG", "$currencies")
            } catch (e: Exception){
                e.printStackTrace()
                Log.d("MY_TAG", e.localizedMessage)
            }
//            print(currencies)
        }
    }
}