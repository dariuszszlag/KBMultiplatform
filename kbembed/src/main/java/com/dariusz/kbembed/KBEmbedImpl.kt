package com.dariusz.kbembed

import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.utils.LoginTest

internal class KBEmbedImpl(
    private val kbEmbedComponents: KBEmbedComponents,
    private val loginTest: LoginTest = LoginTest(kbEmbedComponents)
): KBEmbed {

    override fun getNavigator(): Navigator = kbEmbedComponents.navigator

    override fun login(password: String) = loginTest.loginWithCorrectPassword(password)

}