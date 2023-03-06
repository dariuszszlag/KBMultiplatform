package com.dariusz.kbembed.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextInput(
    placeholder: String = "",
    onValueSubmit: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputString by remember { mutableStateOf("") }

    TextField(
        value = inputString,
        placeholder = { Text(placeholder) },
        onValueChange = {
            inputString = it
        },
        modifier = Modifier
            .padding(25.dp)
            .clickable { keyboardController?.show() },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = { onValueSubmit(inputString) }
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}