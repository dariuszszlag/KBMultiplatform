package com.dariusz.kbembed

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dariusz.kbcore.kbCoreBuilder
import com.dariusz.kbembed.navigation.MainNavHost
import com.dariusz.kbembed.navigation.Navigator
import com.dariusz.kbembed.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers

internal class KBEmbedBuilderImpl(
    private val navigator: Navigator
) : KBEmbedBuilder {

    private lateinit var _activity: ComponentActivity

    override fun setActivity(activity: ComponentActivity): KBEmbedBuilder {
        _activity = activity
        return this
    }

    override fun build(): KBEmbed {
        val kbcore = kbCoreBuilder().coroutineDispatcher(Dispatchers.Main.immediate).build()
        if (!::_activity.isInitialized) {
            throw IllegalArgumentException("Activity not initialized")
        } else {
            _activity.setContent {
                MyApplicationTheme {
                    MainNavHost(kbcore, navigator)
                }
            }
        }
        return KBEmbedImpl(kbcore, navigator)
    }

}