package com.dariusz.kbembed.utils

import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

class LoginTest(
    private val kbCore: KBCore,
    private val navigator: Navigator
) {

    fun loginWithCorrectPassword(password: String) {
        kbCore.getDataForUser(password)
        navigator.navigateHome()
    }

}