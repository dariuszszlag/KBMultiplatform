package com.dariusz.kbembed.utils

import com.dariusz.kbembed.KBEmbedComponents

class LoginTest(private val kbEmbedComponents: KBEmbedComponents) {

    fun loginWithCorrectPassword(password: String) {
        kbEmbedComponents.kbCore.getDataForUser(password)
        kbEmbedComponents.navigator.navigateHome()
    }

}