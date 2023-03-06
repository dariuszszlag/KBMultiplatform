package com.dariusz.kbembed.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalTextApi::class)
@Composable
fun PostOrDraftView(
    id: Int,
    label: String,
    content: String
) {
    val gradientColors = listOf(Cyan, Blue, Magenta)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
    ) {
        Column {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                Text("#$id ")
                Text(label)
                Text(
                    text = " $content ",
                    style = TextStyle(
                        brush = Brush.linearGradient(colors = gradientColors)
                    )
                )

            }
        }
    }
}
