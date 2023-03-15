package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.MainNavHost
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel
import com.dariusz.kbembed.ui.theme.MyApplicationTheme
import com.dariusz.kbembed.utils.LoginUtils
import com.dariusz.kbembed.utils.ComposeViewModel.composeViewModel

internal object KBEmbedBuilderImpl : KBEmbedBuilder {

    private lateinit var _activity: ComponentActivity

    private lateinit var _kbCore: KBCore

    private lateinit var _kbEmbedComponents: KBEmbedComponents

    private lateinit var _loginScreenViewModel: LoginScreenViewModel

    override fun setActivity(activity: ComponentActivity) {
        _activity = activity
    }

    override fun setKBCore(kbCore: KBCore) {
        _kbCore = kbCore
    }

    override fun build(): KBEmbed {
        if (!::_activity.isInitialized) {
            throw IllegalArgumentException("Activity not initialized")
        } else if (!::_kbCore.isInitialized) {
            throw IllegalArgumentException("KBCore not initialized")
        } else {
            _activity.openKB(_kbCore)
        }
       return KBEmbedImpl(_kbEmbedComponents, LoginUtils(_loginScreenViewModel, _kbEmbedComponents.navigator))
    }

    private fun ComponentActivity.openKB(
        kbCore: KBCore
    ) = setContent {
        _kbEmbedComponents = KBEmbedComponents(
            rememberNavController(),
            kbCore
        )
        _loginScreenViewModel = composeViewModel {
            LoginScreenViewModel(_kbEmbedComponents.kbCore)
        }
        MyApplicationTheme {
            MainNavHost(_kbEmbedComponents)
        }
    }

}