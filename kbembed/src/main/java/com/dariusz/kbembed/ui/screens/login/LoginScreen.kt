package com.dariusz.kbembed.ui.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.dariusz.kbembed.ui.components.TextInput
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.Result.Companion.show

@Composable
fun LoginScreen(
    loginScreenState: Result<Boolean>,
    onAlreadyLoggedIn: () -> Unit,
    onPasswordSubmit: (String) -> Unit
) {

    loginScreenState.show {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Text(if (!it) "Not logged in" else "")
            }
            Row {
                TextInput(
                    placeholder = "Provide password",
                    onValueSubmit = onPasswordSubmit
                )
            }
        }

        LaunchedEffect(it) {
            if (it) onAlreadyLoggedIn()
        }
    }

}
