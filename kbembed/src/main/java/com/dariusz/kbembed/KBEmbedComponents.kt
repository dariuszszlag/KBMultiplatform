package com.dariusz.kbembed

import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

data class KBEmbedComponents(
    val kbCore: KBCore,
    val navigator: Navigator
)