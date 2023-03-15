package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import com.dariusz.kbcore.KBCore

interface KBEmbedBuilder {

    fun setActivity(activity: ComponentActivity): KBEmbedBuilder

    fun setKBCore(kbCore: KBCore): KBEmbedBuilder

    fun build(): KBEmbed

}

fun kbEmbedBuilder(): KBEmbedBuilder = KBEmbedBuilderImpl