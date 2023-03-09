package com.dariusz.kbembed.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

object ManualInjection {

    @Composable
    inline fun <reified VM : ViewModel> composeViewModel(crossinline viewModel: () -> VM): VM =
        viewModel(factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModel() as T
            }
        })

}