package com.dariusz.kbmultiapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dariusz.kbcore.Post
import com.dariusz.kbcore.Summary
import com.dariusz.kbcore.getKBCoreBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class MainActivity : ComponentActivity() {

    private val kbCore = getKBCoreBuilder().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        kbCore.getPosts(1)
        kbCore.getSummary(2)
        kbCore.getUserBalance(3)

        val userData: Flow<UserData> = combine(
            kbCore.summaryFlow,
            kbCore.userBalanceFlow,
            kbCore.postsFlow,
        ) { summary, balance, posts ->
            UserData(1, summary, balance, posts)
        }

        setContent {
            MyApplicationTheme {

                val userDataToDisplay by remember { userData }.collectAsState(UserData())

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView("Hello, this is your data: $userDataToDisplay")
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}

data class UserData(
    val id: Int = 0,
    val summary: Summary = Summary(0, ""),
    val balance: Int = 0,
    val posts: List<Post> = listOf()
)