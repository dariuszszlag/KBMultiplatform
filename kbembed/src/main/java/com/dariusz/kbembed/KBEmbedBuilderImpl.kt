package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.MainNavHost
import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.ui.theme.MyApplicationTheme
import com.dariusz.kbembed.utils.LoginTest

internal object KBEmbedBuilderImpl : KBEmbedBuilder {

    private lateinit var _activity: ComponentActivity

    private lateinit var _kbCore: KBCore

    private lateinit var _kbEmbedComponents: KBEmbedComponents

    override fun setActivity(activity: ComponentActivity): KBEmbedBuilder {
        _activity = activity
        return this
    }

    override fun setKBCore(kbCore: KBCore): KBEmbedBuilder {
        _kbCore = kbCore
        return this
    }

    override fun build(): KBEmbed {
        if (!::_activity.isInitialized) {
            throw IllegalArgumentException("Activity not initialized")
        } else if (!::_kbCore.isInitialized) {
            throw IllegalArgumentException("KBCore not initialized")
        } else {
            _activity.apply {
                setContent {
                    val navController = rememberNavController()
                    MyApplicationTheme {
                        MainNavHost(_kbEmbedComponents)
                    }
                    LaunchedEffect(navController, LocalContext.current, _activity, _kbCore) {
                        setKBEmbedComponents(KBEmbedComponents(_kbCore, Navigator(navController)))
                    }
                }
                val loginTest = LoginTest(_kbEmbedComponents)
                return KBEmbedImpl(_kbEmbedComponents.navigator, loginTest)
            }
        }
    }

    private fun setKBEmbedComponents(kbEmbedComponents: KBEmbedComponents) {
        _kbEmbedComponents = kbEmbedComponents
    }
}