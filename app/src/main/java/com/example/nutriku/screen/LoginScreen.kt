package com.example.nutriku.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.nutriku.ui.theme.NutrikuTheme

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
        Column(modifier = modifier.fillMaxHeight().padding(bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputForm(formState = loginViewModel.formState, submitText = "Login", onSubmit = { /*TODO*/ })
            Text(text = "Belum memiliki akun? Buat Akun")
        }
    }
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