package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import com.dariusz.kbcore.KBCore

interface KBEmbedBuilder {

    fun setActivity(activity: ComponentActivity)

    fun setKBCore(kbCore: KBCore)

    fun build(): KBEmbed

}

fun kbEmbedBuilder(): KBEmbedBuilder = KBEmbedBuilderImpl