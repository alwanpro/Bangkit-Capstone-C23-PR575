package com.example.nutriku.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import ch.benlu.composeform.FieldState
import ch.benlu.composeform.Form
import ch.benlu.composeform.FormField
import ch.benlu.composeform.Validator
import ch.benlu.composeform.fields.PasswordField
import ch.benlu.composeform.fields.TextField
import ch.benlu.composeform.validators.EmailValidator
import ch.benlu.composeform.validators.MinLengthValidator
import ch.benlu.composeform.validators.NotEmptyValidator
import com.example.nutriku.ui.theme.NutrikuTheme
import java.lang.reflect.Type

class InputItem(val label: String, val type: Type, val hint: String? = "")
data class FormState(val formValueMap: MutableMap<String, String?>) {
    var formState = formValueMap
}

open class MainForm : Form() {
    override fun self(): Form {
        return this
    }

    @FormField
    val username = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(NotEmptyValidator())
    )

    @FormField
    val password = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            MinLengthValidator(8, "The passwd must be at least 8 chars.")
        )
    )
}

class SignupForm : MainForm() {
    @FormField
    val email = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            EmailValidator()
        )
    )


    @FormField
    val repeatPassword = FieldState(
        state = mutableStateOf<String?>(null),
        validators = mutableListOf(
            NotEmptyValidator(),
            RepeatPasswordValidator(password = this.password.state.value)
        )
    )
    class RepeatPasswordValidator(errorText: String? = null, password: String? = "") : Validator<String?>(
        validate = {
            password?.toRegex()?.matches("$it") ?: false
        },
        errorText = errorText ?: "Password does not match"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(
    formState: MainForm,
    submitText: String,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(
                label = "Username",
                form = formState,
                fieldState = formState.username,
                modifier = modifier.padding(bottom = 16.dp)
            ).Field()

            if (formState is SignupForm) {
                TextField(
                    label = "Email",
                    form = formState,
                    fieldState = formState.email,
                    modifier = modifier.padding(bottom = 16.dp)
                ).Field()
            }

            PasswordField(
                label = "Password",
                form = formState,
                fieldState = formState.password,
                modifier = modifier.padding(bottom = 16.dp)
            ).Field()

            if (formState is SignupForm) {
                PasswordField(
                    label = "Repeat Password",
                    form = formState,
                    fieldState = formState.repeatPassword,
                    modifier = modifier.padding(bottom = 16.dp)
                ).Field()
            }

            PrimaryButton(modifier = modifier, buttonText = submitText, onClick = onSubmit)
        }
    } 



}


@Preview
@Composable
fun InputFormPreview() {
    NutrikuTheme {
        //InputForm()
    }
}
