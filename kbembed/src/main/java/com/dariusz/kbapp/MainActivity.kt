package com.dariusz.kbapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.dariusz.kbmultiapp.KBCore
import com.dariusz.kbmultiapp.getKBCoreBuilder
import com.dariusz.kbui.MainState
import com.dariusz.kbui.MainView
import com.dariusz.kbui.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine

class MainActivity : ComponentActivity() {

    private val kbCore: KBCore = getKBCoreBuilder().setCoroutineScope(CoroutineScope(Dispatchers.Main.immediate)).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView(MainState(kbCore.generateState(userId = 1)))
                }
            }
        }
    }

    @Composable
    private fun KBCore.generateState(userId: Int): UserData {
        getUserBalance(userId)
        getSummary(userId)
        getPosts(userId)
        val userDataFlow = combine(
            userBalanceFlow,
            summaryFlow,
            postsFlow
        ) { balance, summary, posts ->
            UserData(userId, summary, balance, posts)
        }
        val userDataState by remember { userDataFlow }.collectAsState(UserData())
        return userDataState
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}

