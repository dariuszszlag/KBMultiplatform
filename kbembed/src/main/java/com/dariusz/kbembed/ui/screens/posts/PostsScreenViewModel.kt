package com.dariusz.kbembed.ui.screens.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dariusz.kbcore.KBCore
import com.dariusz.kbcore.feature.post.Post
import com.dariusz.kbembed.utils.Result
import com.dariusz.kbembed.utils.ViewState
import com.dariusz.kbembed.utils.ViewStateUtils.asResult
import com.dariusz.kbembed.utils.ViewStateUtils.onError
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PostsScreenViewModel(
    dataSource: KBCore
) : ViewModel() {

    val postsScreenState = dataSource.userPostsFlow
        .map { posts -> posts.ifEmpty { listOf() } }
        .asResult()
        .onError("Error when downloading posts")
        .map { PostsScreenState(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            PostsScreenState()
        )

}

data class PostsScreenState(
    override val data: Result<List<Post>> = Result.Loading
) : ViewState<List<Post>>