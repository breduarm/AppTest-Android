package com.beam.tictactoe.ui.screens.game

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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LineStyle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
                text = if (text == "X") "" else "X"
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
    Square()
}

