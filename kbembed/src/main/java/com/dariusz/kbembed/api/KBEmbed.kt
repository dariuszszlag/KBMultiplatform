package com.dariusz.kbembed.api

import com.dariusz.kbembed.navigation.Navigator

interface KBEmbed {

    fun getNavigator(): Navigator

    fun login(password: String)

    fun logout()

}
