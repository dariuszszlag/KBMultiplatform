package com.dariusz.kbembed

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

open class KBEmbedComponentsProvider {

    private lateinit var _navHostController: NavHostController

    private lateinit var _kbCore: KBCore

    @Composable
    fun setNavHostController() {
        _navHostController = rememberNavController()
    }

    fun setKbCore(kbCore: KBCore) {
        _kbCore = kbCore
    }

    val kbEmbedComponents = KBEmbedComponents(_kbCore, Navigator(_navHostController))

}