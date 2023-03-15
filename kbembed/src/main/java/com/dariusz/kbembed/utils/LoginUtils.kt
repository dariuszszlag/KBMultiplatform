package com.dariusz.kbembed.utils

import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel

class LoginUtils(
    private val viewModel: LoginScreenViewModel,
    private val navigator: Navigator
) {

    fun loginWithCorrectPassword(password: String) {
        viewModel.providePassword(password)
        navigator.navigateHome()
    }

}