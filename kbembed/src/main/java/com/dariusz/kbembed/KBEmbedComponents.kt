package com.dariusz.kbembed

import androidx.navigation.NavController
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

data class KBEmbedComponents(
    val navController: NavController,
    val kbCore: KBCore,
    val navigator: Navigator = Navigator(navController)
)
