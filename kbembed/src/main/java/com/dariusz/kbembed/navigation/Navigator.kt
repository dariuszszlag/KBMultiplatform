package com.dariusz.kbembed.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class Navigator : ViewModel() {

    val currentDestination = mutableStateOf(Destination.HOME.route)

    fun navigateHome() = navigateTo(Destination.HOME)

    fun navigateToDrafts() = navigateTo(Destination.DRAFTS)

    fun navigateToDraft(draftId: Int) = navigateTo(Destination.DRAFTS, draftId.toString())

    fun navigateToPosts() = navigateTo(Destination.POSTS)

    fun navigateToPost(postId: Int) = navigateTo(Destination.POSTS, postId.toString())

    fun navigateToLogin() = navigateTo(Destination.LOGIN)

    private fun navigateTo(destination: Destination) {
        currentDestination.value = destination.route
    }

    private fun navigateTo(destination: Destination, args: String? = null) {
        currentDestination.value = destination.route + "/" + args
    }

}