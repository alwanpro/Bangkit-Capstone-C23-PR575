package com.example.nutriku.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.nutriku.component.InputForm
import com.example.nutriku.component.MainForm
import com.example.nutriku.component.NutrikuBadge
import com.example.nutriku.component.TextLink
import com.example.nutriku.data.ApiConfig
import com.example.nutriku.data.AuthResponse
import com.example.nutriku.ui.theme.NutrikuTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val loginViewModel = LoginViewModel()

        Box(modifier.padding(vertical = 36.dp)) {
            NutrikuBadge(modifier)
        }
        Text(text = "Login")
        Column(modifier = modifier
            .fillMaxHeight()
            .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputForm(formState = loginViewModel.formState, submitText = "Login", onSubmit = { /*TODO*/ })
            Row() {
                Text(text = "Belum memiliki akun? ")
                TextLink(modifier = modifier, text = "Buat Akun", onClick = { submitLogin(loginViewModel.formState.username.toString(), loginViewModel.formState.password.toString()) })
            }
        }
    }
}
fun submitLogin(name : String, password : String) {
    val client = ApiConfig.getApiService().login(name, password)
    client.enqueue(object: Callback<AuthResponse> {
        override fun onResponse(
            call: Call<AuthResponse>,
            response: Response<AuthResponse>
        ) {
            val responseBody = response.body()
            if (response.isSuccessful && responseBody?.data != null) {
                // TODO: save token and go to home screen
            } else {
                Log.e("LoginScreen", "onFailure: ${responseBody?.message}")
            }
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Log.e("LoginScreen", "Login Error")
        }
    })
}

class LoginViewModel: ViewModel() {
    val formState = MainForm()
}

@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {

    NutrikuTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen(Modifier)
        }
    }
}