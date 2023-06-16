package com.example.nutriku.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import java.lang.IllegalArgumentException

class MyViewModelFactory constructor(val context: Context) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MyViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): MyViewModelFactory {
            if(INSTANCE == null) {
                synchronized(MyViewModelFactory::class.java) {
                    INSTANCE = MyViewModelFactory(context)
                }
            }
            return INSTANCE as MyViewModelFactory
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}