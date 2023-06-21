package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import com.dariusz.kbembed.navigation.Navigator

interface KBEmbedBuilder {

    fun setActivity(activity: ComponentActivity): KBEmbedBuilder

    fun build(): KBEmbed

}

fun kbEmbedBuilder(): KBEmbedBuilder = KBEmbedBuilderImpl(Navigator())