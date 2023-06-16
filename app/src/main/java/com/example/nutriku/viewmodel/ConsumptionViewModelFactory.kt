package com.example.nutriku.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ConsumptionViewModelFactory private constructor(private val foodclass: String) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ConsumptionViewModelFactory? = null

        @JvmStatic
        fun getInstance(foodclass: String): ConsumptionViewModelFactory {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = ConsumptionViewModelFactory(foodclass = foodclass)
                }
            }
            return INSTANCE as ConsumptionViewModelFactory
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ConsumptionScreenViewModel::class.java)) {
            return ConsumptionScreenViewModel(foodclass) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}