package com.dariusz.kbembed.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

interface ViewState<T> {
    val data: Result<T>
}

object ViewStateUtils {

    fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> {
        Result.Success(it)
    }

    fun <T> Flow<Result<T>>.onError(message: String) = catch {
        Result.Error(message)
    }

    @Composable
    fun <T> ViewState<T>.show(content: @Composable (T) -> Unit) = when (data) {
        is Result.Success -> {
            content((data as Result.Success<T>).data)
        }

        is Result.Error -> {
            CenteredText("Error: ${(data as Result.Error).errorMessage}")
        }

        is Result.Loading -> LoadingComponent()
    }

    @Composable
    private fun LoadingComponent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.wrapContentWidth(
                    Alignment.CenterHorizontally
                )
            )
        }
    }

    @Composable
    fun CenteredText(
        text: String
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text, modifier = Modifier.wrapContentWidth(
                    Alignment.CenterHorizontally
                ), color = Color.White
            )
        }
    }

}
