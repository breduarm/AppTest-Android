package com.beam.tictactoe.ui.screens.board

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BoardScreen() {
    Column {
        Row {
            Square()
            Square()
            Square()
        }
        Row {
            Square()
            Square()
            Square()
        }
        Row {
            Square()
            Square()
            Square()
        }
    }
}

@Composable
fun Square() {
    var text: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .border(1.dp, Color.Black)
            .clickable {
                text = "X"
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun BoardScreenPreview() {
    BoardScreen()
}

