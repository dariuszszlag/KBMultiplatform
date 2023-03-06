package com.dariusz.kbembed.ui.screens.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dariusz.kbembed.utils.ViewStateUtils.show

@Composable
fun HomeScreen(
    homeScreenState: HomeScreenState,
    onNotLoggedIn: () -> Unit,
    onNavigatePosts: () -> Unit,
    onNavigateDrafts: () -> Unit,
    onLogout: () -> Unit
) {

    homeScreenState.show {
        MainView(it, onNotLoggedIn, onNavigatePosts, onNavigateDrafts, onLogout)
    }

    BackHandler {}

}

@Composable
private fun MainView(
    userData: UserData,
    onNotLoggedIn: () -> Unit,
    onNavigatePosts: () -> Unit,
    onNavigateDrafts: () -> Unit,
    onLogout: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("For user #${userData.id}")
            Text("Balance: ${userData.balance}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Number of drafts: ${userData.draftsCount}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text("Number of posts: ${userData.postsCount}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onNavigatePosts() }) {
                Text("Go to posts")
            }
            Button(onClick = { onNavigateDrafts() }) {
                Text("Go to drafts")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { onLogout() }) {
                Text("Logout")
            }
        }
    }

    LaunchedEffect(userData.id) {
        if (userData.id == 0) onNotLoggedIn()
    }
}