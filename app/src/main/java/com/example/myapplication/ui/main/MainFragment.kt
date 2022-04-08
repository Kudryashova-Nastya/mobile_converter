package com.example.myapplication.ui.main

//import androidx.lifecycle.ViewModelProvider
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.myapplication.DependencyInjection
//import com.example.myapplication.R
//
//class MainFragment : Fragment() {
//
//
//    private lateinit var viewModel: MainViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val viewModelFactory =
//            MainViewModelFactory(DependencyInjection.repository) // пока нет внутреннего хранилища - идёт в сеть
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
//        viewModel.init() // получил данные
//        viewModel.liveData.observe(viewLifecycleOwner) { response ->
//            Log.d("MY_TAG", response.currencyList.toString())
//        }
//        return inflater.inflate(R.layout.main_fragment, container, false)
//    }
//
//    companion object {
//        fun newInstance() = MainFragment()
//    }
//
//}