package com.example.mygame

import com.example.mygame.figure.*
import com.example.mygame.figure.common.Figure

class Board {
    companion object {
        var gameBoard = arrayOf<Array<Figure>>()

        fun swap(x1: Int, y1: Int, x2: Int, y2: Int) {
            var tmp = gameBoard[x1][y1]
            gameBoard[x2][y2] = tmp
            gameBoard[x1][y1] = gameBoard[x2][y2]
        }

        fun initialize() {
            for (i in 0..7) {
                var array = arrayOf<Figure>()
                for (j in 0..7) {
                    when (i) {
                        6 -> {
                            array += Pawn(j, i, "white") //white pawn
                        }
                        1 -> {
                            array += Pawn(j, i, "black") //black pawn
                        }
                        7 -> {
                            when (j) {
                                0, 7 -> {
                                    array += Rook(j, i, "white")// white rook
                                }
                                1, 6 -> {
                                    array += Knight(j, i, "white") // white knight
                                }
                                2, 5 -> {
                                    array += Bishop(j, i, "white") // white bishop
                                }
                                3 -> {
                                    array += Queen(j, i, "white") // white queen
                                }
                                4 -> {
                                    array += King(j, i, "white") // white king
                                }
                            }
                        }
                        0 -> {
                            when (j) {
                                0, 7 -> {
                                    array += Rook(j, i, "black") // black rook
                                }
                                1, 6 -> {
                                    array += Knight(j, i, "black") // black knight
                                }
                                2, 5 -> {
                                    array += Bishop(j, i, "black") // black bishop
                                }
                                3 -> {
                                    array += Queen(j, i, "black") // black queen
                                }
                                4 -> {
                                    array += King(j, i, "black") // black king
                                }
                            }
                        }
                        else -> {
                            array += Empty(0, 0, "white")
                        }
                    }
                }
                gameBoard += array
            }
        }
    }
}