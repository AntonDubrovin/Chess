package com.example.mygame

import com.example.mygame.figure.*
import com.example.mygame.figure.common.AbstractFigure

class Board {
    companion object {
        var gameBoard = arrayOf<Array<AbstractFigure>>()

        fun initialize() {
            for (i in 0..7) {
                var array = arrayOf<AbstractFigure>()
                for (j in 0..7) {
                    when (i) {
                        6 -> {
                            array += Pawn(j, i, FigureColor.WHITE) //white pawn
                        }
                        1 -> {
                            array += Pawn(j, i, FigureColor.BLACK) //black pawn
                        }
                        7 -> {
                            when (j) {
                                0, 7 -> {
                                    array += Rook(j, i, FigureColor.WHITE)// white rook
                                }
                                1, 6 -> {
                                    array += Knight(j, i, FigureColor.WHITE) // white knight
                                }
                                2, 5 -> {
                                    array += Bishop(j, i, FigureColor.WHITE) // white bishop
                                }
                                3 -> {
                                    array += Queen(j, i, FigureColor.WHITE) // white queen
                                }
                                4 -> {
                                    array += King(j, i, FigureColor.WHITE) // white king
                                }
                            }
                        }
                        0 -> {
                            when (j) {
                                0, 7 -> {
                                    array += Rook(j, i, FigureColor.BLACK) // black rook
                                }
                                1, 6 -> {
                                    array += Knight(j, i, FigureColor.BLACK) // black knight
                                }
                                2, 5 -> {
                                    array += Bishop(j, i, FigureColor.BLACK) // black bishop
                                }
                                3 -> {
                                    array += Queen(j, i, FigureColor.BLACK) // black queen
                                }
                                4 -> {
                                    array += King(j, i, FigureColor.BLACK) // black king
                                }
                            }
                        }
                        else -> {
                            array += Empty(0, 0, FigureColor.EMPTY)
                        }
                    }
                }
                gameBoard += array
            }
        }
    }
}