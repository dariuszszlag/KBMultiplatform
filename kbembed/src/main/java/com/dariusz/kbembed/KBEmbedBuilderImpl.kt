package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.MainNavHost
import com.dariusz.kbembed.ui.screens.login.LoginScreenViewModel
import com.dariusz.kbembed.ui.theme.MyApplicationTheme
import com.dariusz.kbembed.utils.LoginUtils
import com.dariusz.kbembed.utils.ComposeViewModel.composeViewModel

object KBEmbedBuilderImpl : KBEmbedBuilder {

    private lateinit var _activity: ComponentActivity

    private lateinit var _kbCore: KBCore

    private lateinit var _kbEmbedSettings: KBEmbedSettings

    private lateinit var _loginScreenViewModel: LoginScreenViewModel

    override fun setActivity(activity: ComponentActivity): KBEmbedBuilderImpl {
        _activity = activity
        return this
    }

    override fun setKBCore(kbCore: KBCore): KBEmbedBuilderImpl {
        _kbCore = kbCore
        return this
    }

    override fun build(): KBEmbed = if (!::_activity.isInitialized) {
        throw IllegalArgumentException("Activity not initialized")
    } else if (!::_kbCore.isInitialized) {
        throw IllegalArgumentException("KBCore not initialized")
    } else {
        _activity.openKB(_kbCore)
        KBEmbedImpl(_kbEmbedSettings, LoginUtils(_loginScreenViewModel, _kbEmbedSettings.navigator))
    }

    private fun ComponentActivity.openKB(
        kbCore: KBCore
    ) = setContent {
        _kbEmbedSettings = rememberKBEmbedSettings(kbCore)
        _loginScreenViewModel = composeViewModel {
            LoginScreenViewModel(_kbEmbedSettings.kbCore)
        }
        MyApplicationTheme {
            MainNavHost(_kbEmbedSettings)
        }
    }

}