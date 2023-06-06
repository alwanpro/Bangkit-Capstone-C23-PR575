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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.nutriku.component.InputForm
import com.example.nutriku.component.MainForm
import com.example.nutriku.component.NutrikuBadge
import com.example.nutriku.component.SignupForm
import com.example.nutriku.ui.theme.NutrikuTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val signupForm = SignupViewModel()

        Box(modifier.padding(vertical = 36.dp)) {
            NutrikuBadge(modifier)
        }
        Text(text = "Sign Up")
        Column(modifier = modifier.fillMaxHeight().padding(bottom = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputForm(formState = signupForm.formState, submitText = "Sign Up", onSubmit = { /*TODO*/ })
            Text(text = "Sudah memiliki akun? Masuk dengan Akun")
        }
    }
}

class SignupViewModel: ViewModel() {
    val formState = SignupForm()
}

@Preview(showSystemUi = true)
@Composable
fun SignupPreview() {

    NutrikuTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            SignUpScreen(Modifier)
        }
    }
}