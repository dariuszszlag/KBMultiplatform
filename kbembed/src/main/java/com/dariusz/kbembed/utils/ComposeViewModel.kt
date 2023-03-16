package com.dariusz.kbembed.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.ui.screens.drafts.DraftsScreenViewModel
import com.dariusz.kbembed.ui.screens.home.HomeScreenViewModel
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel
import com.dariusz.kbembed.ui.screens.posts.PostsScreenViewModel
import kotlin.reflect.KClass

object ComposeViewModel {

    @Composable
    inline fun <reified VM : ViewModel> KBCore.getViewModel(clazz: KClass<VM>): VM = when (clazz) {
        DraftsScreenViewModel::class -> composeViewModel { DraftsScreenViewModel(this) } as VM
        HomeScreenViewModel::class -> composeViewModel { HomeScreenViewModel(this) } as VM
        LoginScreenViewModel::class -> composeViewModel { LoginScreenViewModel(this) } as VM
        PostsScreenViewModel::class -> composeViewModel { PostsScreenViewModel(this) } as VM
        else -> throw IllegalArgumentException("No such viewmodel")
    }

    @Composable
    inline fun <reified VM : ViewModel> composeViewModel(crossinline viewModel: () -> VM): VM =
        viewModel(factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel() as T
            }
        })

}