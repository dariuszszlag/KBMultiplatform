package com.dariusz.kbembed.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dariusz.kbembed.data.KBCoreDataSource
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreen
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreenViewModel
import com.dariusz.kbembed.ui.screens.home.HomeScreen
import com.dariusz.kbembed.ui.screens.home.HomeScreenViewModel
import com.dariusz.kbembed.ui.screens.login.LoginScreen
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel
import com.dariusz.kbembed.ui.screens.posts.PostsScreen
import com.dariusz.kbembed.ui.screens.posts.PostsScreenViewModel
import com.dariusz.kbembed.utils.ManualInjection.composeViewModel

@Composable
fun Navigator() {
    val navController = rememberNavController()
    val dataSource = KBCoreDataSource.get
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
        navController = navController,
        startDestination = Destination.HOME.route
    ) {
        composable(Destination.HOME.route) {
            val homeScreenState by remember(homeScreenViewModel) { homeScreenViewModel.homeScreenState }.collectAsState()
            HomeScreen(
                homeScreenState = homeScreenState,
                onNotLoggedIn = { navController.navigateTo(Destination.LOGIN) },
                onNavigatePosts = { navController.navigateTo(Destination.POSTS) },
                onNavigateDrafts = { navController.navigateTo(Destination.DRAFTS) },
                onLogout = {
                    homeScreenViewModel.logout()
                    navController.navigateTo(Destination.LOGIN)
                }
            )
        }
        composable(Destination.DRAFTS.route) {
            val draftsScreenState by remember(draftsScreenViewModel) { draftsScreenViewModel.draftsScreenState }.collectAsState()
            DraftsScreen(
                draftsScreenState = draftsScreenState,
                onGoBack = { navController.navigateTo(Destination.HOME) }
            )
        }
        composable(Destination.POSTS.route) {
            val postScreenState by remember(postsScreenViewModel) { postsScreenViewModel.postsScreenState }.collectAsState()
            PostsScreen(
                postScreenState = postScreenState,
                onGoBack = { navController.navigateTo(Destination.HOME) }
            )
        }
        composable(Destination.LOGIN.route) {
            val loginScreenState by remember(loginScreenViewModel) { loginScreenViewModel.loginScreenState }.collectAsState()
            LoginScreen(
                loginScreenState = loginScreenState,
                onAlreadyLoggedIn = { navController.navigateTo(Destination.HOME) },
                onPasswordSubmit = loginScreenViewModel::providePassword
            )
        }
    }
}

private enum class Destination(val route: String) {
    LOGIN("login"),
    HOME("home"),
    DRAFTS("drafts"),
    POSTS("posts")
}

private fun NavController.navigateTo(destination: Destination) =
    navigate(destination.route)

private fun NavController.navigateTo(destination: Destination, args: String? = null) =
    navigate(destination.route + "/" + args)

