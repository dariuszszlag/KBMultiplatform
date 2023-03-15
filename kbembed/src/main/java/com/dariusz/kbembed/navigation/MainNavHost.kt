package com.dariusz.kbembed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.kbembed.KBEmbedSettings
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreen
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreenViewModel
import com.dariusz.kbembed.ui.screens.home.HomeScreen
import com.dariusz.kbembed.ui.screens.home.HomeScreenViewModel
import com.dariusz.kbembed.ui.screens.login.LoginScreen
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel
import com.dariusz.kbembed.ui.screens.posts.PostsScreen
import com.dariusz.kbembed.ui.screens.posts.PostsScreenViewModel
import com.dariusz.kbembed.utils.ComposeViewModel.composeViewModel

@Composable
fun MainNavHost(kbEmbedSettings: KBEmbedSettings) {
    val dataSource = kbEmbedSettings.kbCore
    val navigator = kbEmbedSettings.navigator
    val homeScreenViewModel = composeViewModel {
        HomeScreenViewModel(dataSource)
    }
    val draftsScreenViewModel = composeViewModel {
        DraftsScreenViewModel(dataSource)
    }
    val postsScreenViewModel = composeViewModel {
        PostsScreenViewModel(dataSource)
    }
    val loginScreenViewModel = composeViewModel {
        LoginScreenViewModel(dataSource)
    }
    NavHost(
        navController = kbEmbedSettings.navController as NavHostController,
        startDestination = Destination.HOME.route
    ) {
        composable(Destination.HOME.route) {
            val homeScreenState by remember(homeScreenViewModel) { homeScreenViewModel.homeScreenState }.collectAsState()
            HomeScreen(
                homeScreenState = homeScreenState,
                onNotLoggedIn = { navigator.navigateHome() },
                onNavigatePosts = { navigator.navigateToPosts() },
                onNavigateDrafts = { navigator.navigateToDrafts() },
                onLogout = {
                    homeScreenViewModel.logout()
                    navigator.navigateToLogin()
                }
            )
        }
        composable(Destination.DRAFTS.route) {
            val draftsScreenState by remember(draftsScreenViewModel) { draftsScreenViewModel.draftsScreenState }.collectAsState()
            DraftsScreen(
                draftsScreenState = draftsScreenState,
                onGoBack = { navigator.navigateHome() }
            )
        }
        composable(Destination.POSTS.route) {
            val postScreenState by remember(postsScreenViewModel) { postsScreenViewModel.postsScreenState }.collectAsState()
            PostsScreen(
                postScreenState = postScreenState,
                onGoBack = { navigator.navigateHome() }
            )
        }
        composable(Destination.LOGIN.route) {
            val loginScreenState by remember(loginScreenViewModel) { loginScreenViewModel.loginScreenState }.collectAsState()
            LoginScreen(
                loginScreenState = loginScreenState,
                onAlreadyLoggedIn = { navigator.navigateHome() },
                onPasswordSubmit = loginScreenViewModel::providePassword
            )
        }
    }
}
