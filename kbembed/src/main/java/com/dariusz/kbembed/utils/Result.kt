package com.dariusz.kbembed.utils

import androidx.compose.runtime.Composable
import com.dariusz.kbembed.ui.components.CenteredText
import com.dariusz.kbembed.ui.components.LoadingComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {

        fun <T> Flow<T>.asResult(
            onErrorMessage: String? = null
        ): Flow<Result<T>> = this
            .map<T, Result<T>> {
                Success(it)
            }
            .catch {
                Error(onErrorMessage ?: "Unknown Error")
            }

        @Composable
        fun <T> Result<T>.show(content: @Composable (T) -> Unit) = when (this) {
            is Success -> {
                content(data)
            }

            is Error -> {
                CenteredText("Error: $errorMessage")
            }

            is Loading -> LoadingComponent()
        }
    }
}