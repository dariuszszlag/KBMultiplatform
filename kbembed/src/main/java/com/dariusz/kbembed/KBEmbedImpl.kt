package com.dariusz.kbembed

import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.utils.LoginTest

internal class KBEmbedImpl(
    private val navigator: Navigator,
    private val loginTest: LoginTest
): KBEmbed {
    override fun getNavigator(): Navigator = navigator

    override fun login(password: String) = loginTest.loginWithCorrectPassword(password)

}