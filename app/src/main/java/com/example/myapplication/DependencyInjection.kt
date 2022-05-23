package com.example.myapplication

import com.example.myapplication.data.retrofit.CurrencyApi
//import com.example.myapplication.data_source.LocalDataSource
import com.example.myapplication.data.retrofit.RemoteDataSource
import com.example.myapplication.domain.repository.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjection {
    private val interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://data.fixer.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: CurrencyApi = retrofit.create(CurrencyApi::class.java)

//    val mNodeViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
//    private val db: CurrencyDao = CurrencyViewModel.repository
//    private val db: RoomCurrencyRepository = mNodeViewModel.repository

//    private val localDataSource = LocalDataSource(db)
//    private val localDataSource = db
    private val remoteDataSource = RemoteDataSource(service)

    val repository = Repository(remoteDataSource)
//    override fun getViewModelStore(): ViewModelStore {
//
//    }
}