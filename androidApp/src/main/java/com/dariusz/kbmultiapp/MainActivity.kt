package com.dariusz.kbmultiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dariusz.kbembed.kbEmbedBuilder

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        kbEmbedBuilder()
            .setActivity(this)
            .build()

    }
}
