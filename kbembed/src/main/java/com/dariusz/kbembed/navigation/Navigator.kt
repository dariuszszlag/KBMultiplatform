package com.dariusz.kbembed.navigation

import androidx.navigation.NavController

class Navigator(private val navController: NavController) {

    fun navigateHome() = navController.navigateTo(Destination.HOME)

    fun navigateToDrafts() = navController.navigateTo(Destination.DRAFTS)

    fun navigateToDraft(draftId: Int) = navController.navigateTo(Destination.DRAFTS, draftId.toString())

    fun navigateToPosts() = navController.navigateTo(Destination.POSTS)

    fun navigateToPost(postId: Int) = navController.navigateTo(Destination.POSTS, postId.toString())

    fun navigateToLogin() = navController.navigateTo(Destination.LOGIN)

    private fun NavController.navigateTo(destination: Destination) =
        navigate(destination.route)

    private fun NavController.navigateTo(destination: Destination, args: String? = null) =
        navigate(destination.route + "/" + args)

}