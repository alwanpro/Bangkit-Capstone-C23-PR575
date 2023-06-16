package com.example.nutriku.viewmodel

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutriku.data.UserData
import kotlinx.coroutines.flow.Flow

class AuthViewModel(context : Context): ViewModel() {

    companion object {
        private const val PREF_NAME = "userPref"
        private const val PREF_KEY_USERID = "userId"
        private const val PREF_KEY_TOKEN = "token"
    }

    val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val _loggedIn = MutableLiveData<Boolean>(false)
    val loggedIn : LiveData<Boolean> = _loggedIn

    private val _token = MutableLiveData<String>("")
    val token : LiveData<String> = _token

    init {
        _token.value = preferences.getString(PREF_KEY_TOKEN, "")
        if (!token.value.isNullOrEmpty()) {
            _loggedIn.value = true
        }
    }


    fun setUserPreferences(model: UserData) {
        val editor = preferences.edit()
        editor.putString(PREF_KEY_USERID, model.userId)
        editor.putString(PREF_KEY_TOKEN, model.token)
        editor.apply()

        _token.value = preferences.getString(PREF_KEY_TOKEN, "")
        if (!token.value.isNullOrEmpty()) {
            _loggedIn.value = true
        }
    }

    fun deleteUserPreferences() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()

        _loggedIn.value = false
        _token.value = preferences.getString(PREF_KEY_TOKEN, "")
    }
}