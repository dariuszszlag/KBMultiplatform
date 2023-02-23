package com.dariusz.kbui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dariusz.kbmultiapp.Post
import com.dariusz.kbmultiapp.Summary

@Composable
fun MainView(mainState: MainState) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("For user #${mainState.data.id}")
            Text("Balance: $${mainState.data.balance}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Summary: ${mainState.data.summary}")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            items(mainState.data.posts) {
                Text(it.post)
            }
        }
    }
}

data class MainState(val data: UserData)

data class UserData(
    val id: Int = 0,
    val summary: Summary = Summary(0, ""),
    val balance: Int = 0,
    val posts: List<Post> = listOf()
)
