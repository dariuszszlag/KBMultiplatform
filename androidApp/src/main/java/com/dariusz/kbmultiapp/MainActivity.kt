package com.dariusz.kbmultiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dariusz.kbcore.KBCoreBuilder
import com.dariusz.kbembed.KBEmbed
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kbCore = KBCoreBuilder.coroutineDispatcher(Dispatchers.IO).build()

        KBEmbed.apply {
            openKB(kbCore)
        }

    }
}
