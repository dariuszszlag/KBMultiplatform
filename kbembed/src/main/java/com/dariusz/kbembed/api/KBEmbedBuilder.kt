package com.dariusz.kbembed.api

import androidx.activity.ComponentActivity
import com.dariusz.kbcore.KBCore
import com.dariusz.kbembed.navigation.Navigator

interface KBEmbedBuilder {

    fun setActivity(activity: ComponentActivity): KBEmbedBuilder

    fun setKBCore(kbCore: KBCore): KBEmbedBuilder

    fun build(): KBEmbed

}

fun kbEmbedBuilder(): KBEmbedBuilder = KBEmbedBuilderImpl(Navigator())