package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.ui.theme.MyApplicationTheme

object KBEmbed {

    fun openKB(activity: ComponentActivity) = activity.setContent {
        MyApplicationTheme {
            Navigator()
        }
    }

}
