package com.dariusz.kbembed.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.Result.Companion.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val dataSource: KBCore
) : ViewModel() {

    private val userId = MutableStateFlow(0)

    val loginScreenState = userId
        .map { it != 0 }
        .asResult(onErrorMessage = "Error determining login state")
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Result.Loading
        )

    fun providePassword(password: String) {
        dataSource.getDataForUser(password)
        viewModelScope.launch {
            dataSource.userDataFlow.collect {
                userId.value = it.id
            }
        }
    }

}
