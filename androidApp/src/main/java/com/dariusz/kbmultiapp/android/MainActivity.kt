package com.dariusz.kbmultiapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dariusz.kbembed.KBEmbed

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KBEmbed.openKB(this)

    }
}
