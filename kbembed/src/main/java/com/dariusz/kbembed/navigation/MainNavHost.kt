package com.dariusz.kbembed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dariusz.kbembed.KBEmbedComponents
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreen
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreenViewModel
import com.dariusz.kbembed.ui.screens.home.HomeScreen
import com.dariusz.kbembed.ui.screens.home.HomeScreenViewModel
import com.dariusz.kbembed.ui.screens.login.LoginScreen
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel
import com.dariusz.kbembed.ui.screens.posts.PostsScreen
import com.dariusz.kbembed.ui.screens.posts.PostsScreenViewModel
import com.dariusz.kbembed.utils.ComposeViewModel.getViewModel

@Composable
fun MainNavHost(kbEmbedComponents: KBEmbedComponents) {
    val dataSource = kbEmbedComponents.kbCore
    val navigator = kbEmbedComponents.navigator
    val homeScreenViewModel = dataSource.getViewModel(HomeScreenViewModel::class)
    val draftsScreenViewModel = dataSource.getViewModel(DraftsScreenViewModel::class)
    val postsScreenViewModel = dataSource.getViewModel(PostsScreenViewModel::class)
    val loginScreenViewModel = dataSource.getViewModel(LoginScreenViewModel::class)
    NavHost(
        navController = kbEmbedComponents.navController as NavHostController,
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
