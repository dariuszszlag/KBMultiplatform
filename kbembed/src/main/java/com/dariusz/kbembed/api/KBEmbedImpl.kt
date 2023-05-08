package com.dariusz.kbembed.api

import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

internal class KBEmbedImpl(
    private val kbCore: KBCore,
    private val navigator: Navigator
) : KBEmbed {

    override fun getNavigator(): Navigator = navigator

    override fun login(password: String) {
        kbCore.getDataForUser(password)
        navigator.navigateHome()
    }

    override fun logout() {
        kbCore.logout()
        navigator.navigateToLogin()
    }

}