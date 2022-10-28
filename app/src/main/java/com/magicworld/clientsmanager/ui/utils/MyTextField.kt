package com.magicworld.clientsmanager.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun MyTextField(campo: String, placeholder: String, keyboardType: KeyboardType = KeyboardType.Text, onTextChanged: (String) -> Unit ) {

    TextField(
        value = campo,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = { MyText(placeholder) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        textStyle = TextStyle(fontSize = 20.sp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            placeholderColor = Color(0xFFB2B2B2),
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            )
    )
}


@Composable
fun MyText(text: String) {
    Text(text = text, fontSize = 20.sp)
}
