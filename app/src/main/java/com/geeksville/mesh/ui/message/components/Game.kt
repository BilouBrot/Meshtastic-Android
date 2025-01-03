package com.geeksville.mesh.ui.message.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToe() {
    var board by remember { mutableStateOf(List(3) { MutableList(3) { "" } }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tic Tac Toe"
        )
        board.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable {
                                if (cell.isEmpty() && winner == null) {
                                    board = board.toMutableList().apply {
                                        this[rowIndex][colIndex] = currentPlayer
                                    }
                                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                                    winner = checkWinner(board)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = cell, fontSize = 36.sp)
                    }
                }
            }
        }
        winner?.let {
            Text(text = "Winner: $it", fontSize = 24.sp, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

fun checkWinner(board: List<List<String>>): String? {
    val lines = listOf(
        // Rows
        listOf(board[0][0], board[0][1], board[0][2]),
        listOf(board[1][0], board[1][1], board[1][2]),
        listOf(board[2][0], board[2][1], board[2][2]),
        // Columns
        listOf(board[0][0], board[1][0], board[2][0]),
        listOf(board[0][1], board[1][1], board[2][1]),
        listOf(board[0][2], board[1][2], board[2][2]),
        // Diagonals
        listOf(board[0][0], board[1][1], board[2][2]),
        listOf(board[0][2], board[1][1], board[2][0])
    )
    for (line in lines) {
        if (line[0].isNotEmpty() && line[0] == line[1] && line[1] == line[2]) {
            return line[0]
        }
    }
    return null
}