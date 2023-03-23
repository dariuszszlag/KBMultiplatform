package com.dariusz.sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dariusz.kbembed.kbEmbedBuilder
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        kbEmbedBuilder()
            .setActivity(this)
            .build()

    }
}