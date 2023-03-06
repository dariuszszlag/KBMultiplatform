package com.dariusz.kbmultiapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dariusz.kbembed.KBEmbed
import com.dariusz.kbembed.KBEmbedBuilder

class MainActivity : ComponentActivity() {

    private val kbEmbed: KBEmbed = KBEmbedBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        kbEmbed.openKB(this)

    }
}
