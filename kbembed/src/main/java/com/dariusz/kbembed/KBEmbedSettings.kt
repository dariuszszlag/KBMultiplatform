package com.dariusz.kbembed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

data class KBEmbedSettings(
    val kbCore: KBCore,
    val navController: NavController,
    val navigator: Navigator
)

@Composable
fun rememberKBEmbedSettings(
    kbCore: KBCore,
    navController: NavController = rememberNavController()
) = remember (kbCore, navController) {
    KBEmbedSettings(kbCore, navController, Navigator(navController))
}
