package com.dariusz.kbembed

import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.utils.LoginUtils

class KBEmbedImpl(
    private val kbEmbedSettings: KBEmbedSettings,
    private val loginUtils: LoginUtils
): KBEmbed {

    override fun getNavigator(): Navigator = kbEmbedSettings.navigator

    override fun login(password: String) = loginUtils.loginWithCorrectPassword(password)

}