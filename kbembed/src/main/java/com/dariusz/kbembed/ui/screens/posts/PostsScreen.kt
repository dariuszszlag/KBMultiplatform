package com.dariusz.kbembed.ui.screens.posts

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dariusz.kbcore.feature.post.Post
import com.dariusz.kbembed.ui.components.PostOrDraftView
import com.dariusz.kbembed.utils.ViewStateUtils.show

@Composable
fun PostsScreen(
    postScreenState: PostsScreenState,
    onGoBack: () -> Unit
) {

    postScreenState.show {
        LazyColumn(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
        ) {
            items(it) {
                PostView(it)
            }
        }
    }

    BackHandler {
        onGoBack()
    }

}

@Composable
private fun PostView(post: Post) {
    PostOrDraftView(
        post.id,
        "post",
        post.content
    )
}