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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
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
    NavigationBar(
        containerColor = Color(0xFF6200EA), // Background color similar to the image
        contentColor = Color.White,
        tonalElevation = 8.dp,
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Game") },
            label = { Text(text = "Game") },
            selected = true,
            onClick = { /*TODO*/ },
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
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountBox, contentDescription = "Scoreboard") },
            label = { Text(text = "Scoreboard") },
            selected = false,
            onClick = { /*TODO*/ },
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
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Face, contentDescription = "More Games") },
            label = { Text(text = "More Games") },
            selected = false,
            onClick = { /*TODO*/ },
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

