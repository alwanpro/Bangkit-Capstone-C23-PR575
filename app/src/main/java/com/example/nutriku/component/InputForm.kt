package com.example.nutriku.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import com.example.nutriku.ui.theme.NutrikuTheme
import java.lang.reflect.Type

class InputItem(val label: String, val type: Type, val hint: String? = "")
class FormState(
    formValueMap: Map<String, String?>
)

@Composable
fun InputForm(
    formState: MutableMap<String, String?>,
    inputItems: List<InputItem>,
    submitText: String,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        inputItems.forEach { inputItem ->
            Text(
                text = inputItem.label
            )
            OutlinedTextField(value = formState[inputItem.label] ?: "", onValueChange = {
                formState[inputItem.label] = it
            })
        }
        Button(
            onClick = onSubmit
        ) {
            Text(text = submitText)
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
