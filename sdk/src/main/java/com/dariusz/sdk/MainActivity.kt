package com.dariusz.sdk

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.dariusz.kbcore.kbCoreBuilder
import com.dariusz.kbembed.kbEmbedBuilder
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kbCore = kbCoreBuilder().coroutineDispatcher(Dispatchers.IO).build()

        kbEmbedBuilder()
            .setActivity(this)
            .setKBCore(kbCore)
            .build()
    }

    companion object {
        fun intent(from: Context) = Intent(from, MainActivity::class.java)
    }
}