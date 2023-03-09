package com.dariusz.kbembed.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.Result.Companion.asResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PostsScreenViewModel(
    dataSource: KBCore
) : ViewModel() {

    val postsScreenState = dataSource.userPostsFlow
        .map { posts -> posts.ifEmpty { listOf() } }
        .asResult(onErrorMessage = "Error when downloading posts")
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            Result.Loading
        )

}
