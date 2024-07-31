package com.beam.tictactoe.ui.screens.game

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.beam.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun GameScreen() {
    TicTacToeTheme {
        Scaffold(
            topBar = {
                GameTopAppBar()
            },
            bottomBar = {
                GameBottomNavigationAppBar()
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
                Board()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopAppBar() {
    TopAppBar(
        title = {
            Text(text = "Tic Tac Toe", fontSize = 20.sp)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EA),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = { /* TODO: Handle settings icon click */ }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More")
            }
        }
    )
}

@Composable
fun GameBottomNavigationAppBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Game", "Scoreboard", "More Games")

    NavigationBar(
        containerColor = Color(0xFF6200EA), // Background color similar to the image
        contentColor = Color.White,
        tonalElevation = 8.dp,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        0 -> Icon(Icons.Filled.Games, contentDescription = item)
                        1 -> Icon(Icons.Filled.Scoreboard, contentDescription = item)
                        2 -> Icon(Icons.Filled.VideogameAsset, contentDescription = item)
                    }
                },
                label = { Text(text = item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF6200EA),
                    selectedTextColor = Color.White,
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                    disabledIconColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                )
            )
        }
    }
}

@Composable
fun Board() {
    var winner by remember { mutableStateOf<String?>(null) }
    val moves = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") }
    var isXNext by remember { mutableStateOf(true) }

    fun calculateWinner() {
        val winningCombinations = listOf("012", "345", "678", "036", "147", "258", "048", "246")
        for (combination in winningCombinations) {
            val (a, b, c) = combination.map { moves[it.digitToInt()] }
            if (a.isNotEmpty() && a == b && b == c) {
                winner = a
            }
        }
        winner = null
    }

    fun handleMove(index: Int) {
        if (moves[index].isNotEmpty()) return

        val nextMove = if (isXNext) "X" else "O"
        isXNext = !isXNext
        moves[index] = nextMove

        calculateWinner()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Square(mark = moves[0], onClick = { handleMove(0) })
            Square(mark = moves[1], onClick = { handleMove(1) })
            Square(mark = moves[2], onClick = { handleMove(2) })
        }
        Row {
            Square(mark = moves[3], onClick = { handleMove(3) })
            Square(mark = moves[4], onClick = { handleMove(4) })
            Square(mark = moves[5], onClick = { handleMove(5) })
        }
        Row {
            Square(mark = moves[6], onClick = { handleMove(6) })
            Square(mark = moves[7], onClick = { handleMove(7) })
            Square(mark = moves[8], onClick = { handleMove(8) })
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        Text(
            text = "Next Move: ${if (isXNext) "X" else "O"}",
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
        )
        winner?.let {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp))
            Text(
                text = "Winner: $it",
                fontSize = 24.sp,
                fontWeight = FontWeight.W900,
            )
        }
    }
}

@Composable
fun Square(mark: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .border(1.dp, Color.Black)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = mark,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontWeight = FontWeight.W700,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}

@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    Board()
}

@Preview(showBackground = true)
@Composable
fun SquarePreview() {
    Square(mark = "", onClick = {})
}

