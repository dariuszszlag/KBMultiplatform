package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import com.dariusz.kbcore.KBCore

interface KBEmbedBuilder {

    fun setActivity(activity: ComponentActivity): KBEmbedBuilderImpl

    fun setKBCore(kbCore: KBCore): KBEmbedBuilderImpl

    fun build(): KBEmbed

}