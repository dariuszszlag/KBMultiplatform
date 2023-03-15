package com.dariusz.kbembed

import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.utils.LoginUtils

internal class KBEmbedImpl(
    private val kbEmbedComponents: KBEmbedComponents,
    private val loginUtils: LoginUtils
): KBEmbed {

    override fun getNavigator(): Navigator = kbEmbedComponents.navigator

    override fun login(password: String) = loginUtils.loginWithCorrectPassword(password)

}