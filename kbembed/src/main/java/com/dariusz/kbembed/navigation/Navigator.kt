package com.dariusz.kbembed.navigation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class Navigator : ViewModel() {

    val currentDestination = MutableStateFlow(Destination.HOME.route)

    fun navigateHome() = navigateTo(Destination.HOME)

    fun navigateToDrafts() = navigateTo(Destination.DRAFTS)

    fun navigateToDraft(draftId: Int) = navigateTo(Destination.DRAFTS, draftId.toString())

    fun navigateToPosts() = navigateTo(Destination.POSTS)

    fun navigateToPost(postId: Int) = navigateTo(Destination.POSTS, postId.toString())

    fun navigateToLogin() = navigateTo(Destination.LOGIN)

    private fun navigateTo(destination: Destination) {
        currentDestination.value = destination.route
        Log.e("navigateTo", "destination: ${destination.route}")
    }

    private fun navigateTo(destination: Destination, args: String? = null) {
        currentDestination.value = destination.route + "/" + args
    }

}