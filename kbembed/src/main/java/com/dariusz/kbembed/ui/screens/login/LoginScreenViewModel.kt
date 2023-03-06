package com.dariusz.kbembed.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.ViewState
import com.dariusz.kbembed.utils.ViewStateUtils.asResult
import com.dariusz.kbembed.utils.ViewStateUtils.onError
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
        .asResult()
        .onError("Error determining login state")
        .map { LoginScreenState(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            LoginScreenState()
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

data class LoginScreenState(
    override val data: Result<Boolean> = Result.Loading
) : ViewState<Boolean>

