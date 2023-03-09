package com.dariusz.kbembed.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.Result.Companion.asResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(
    private val dataSource: KBCore
) : ViewModel() {

    val homeScreenState = combine(
        dataSource.userDataFlow,
        dataSource.userBalanceFlow,
        dataSource.userPostsFlow,
        dataSource.userDraftsFlow
    ) { userData, userBalance, userPosts, userDrafts ->
        val userId = userData.id
        val balance = userBalance.fullBalance
        val postsCount = userPosts.count()
        val draftsCount = userDrafts.count()
        if (userId == 0) {
            UserData()
        } else {
            UserData(userId, balance, postsCount, draftsCount)
        }
    }
        .asResult(onErrorMessage = "Error when getting user data")
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Result.Loading
        )

    fun logout() = dataSource.logout()

}

data class UserData(
    val id: Int = 0,
    val balance: String = "",
    val postsCount: Int = 0,
    val draftsCount: Int = 0
)
