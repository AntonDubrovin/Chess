package com.example.mygame.figure.common

import com.example.mygame.board.Board
import com.example.mygame.board.BoardView
import com.example.mygame.figure.Empty
import com.example.mygame.player.common.AbstractPlayer

class MoveMaker(_white: AbstractPlayer, _black: AbstractPlayer) {


    companion object {
        var st: MutableSet<Pair<Int, Int>> = mutableSetOf()
    }

    val white: AbstractPlayer = _white
    val black: AbstractPlayer = _black
    var touchX: Int = -1
    var touchY: Int = -1
    var currentFigure: AbstractFigure = Empty(-1, -1, FigureColor.EMPTY)
    var turn = FigureColor.WHITE
    var flag = false


    private fun findAttack(color: FigureColor) {
        for (i in 0..7) {
            for (j in 0..7) {
                if (Board.gameBoard[i][j] !is Empty && Board.gameBoard[i][j].color == color) {
                    Board.gameBoard[i][j].moveCell(color)
                }
            }
        }
    }


    private fun choose(row: Int, column: Int) {
        when (turn) {
            FigureColor.WHITE -> {
                currentFigure = white.chooseFigure(row, column)
            }
            FigureColor.BLACK -> {
                currentFigure = black.chooseFigure(row, column)
            }
            else -> {

            }
        }
        if (currentFigure !is Empty) {
            flag = true
            touchX = row
            touchY = column
            BoardView.instance.invalidate()
        }
    }

    private fun moveFigure(row: Int, column: Int) {
        if (checkMove(row, column, currentFigure)) {
            when (turn) {
                FigureColor.WHITE -> {
                    if (white.moveFigure(currentFigure, row, column)) {
                        Board.gameBoard[touchY][touchX] =
                            Empty(touchX, touchY, FigureColor.EMPTY)
                        Board.gameBoard[column][row] = currentFigure
                        touchX = -1
                        touchY = -1
                        changeTurn(turn)
                        flag = false
                        st = mutableSetOf()
                        BoardView.instance.invalidate()
                    }
                }
                FigureColor.BLACK -> {
                    if (black.moveFigure(currentFigure, row, column)) {
                        Board.gameBoard[touchY][touchX] =
                            Empty(touchX, touchY, FigureColor.EMPTY)
                        Board.gameBoard[column][row] = currentFigure
                        touchX = -1
                        touchY = -1
                        changeTurn(turn)
                        flag = false
                        st = mutableSetOf()
                        BoardView.instance.invalidate()
                    }

                }
                else -> {
                }
            }
        } else {
            touchX = row
            touchY = column
            currentFigure = Board.gameBoard[column][row]
            flag = true
            BoardView.instance.invalidate()
        }
    }

    private fun changeTurn(turn: FigureColor) {
        if (turn == FigureColor.WHITE) {
            this.turn = FigureColor.BLACK
        } else {
            this.turn = FigureColor.WHITE
        }
    }

    private fun notTurn(turn: FigureColor): FigureColor {
        if (turn == FigureColor.WHITE) {
            return FigureColor.BLACK
        } else if (turn == FigureColor.BLACK) {
            return FigureColor.WHITE
        } else {
            return turn
        }
    }

    fun makeTurn(row: Int, column: Int) {
        findAttack(notTurn(turn))
        if (row in 0..7 && column in 0..7) {
            if (!flag) {
                choose(row, column)
            } else {
                moveFigure(row, column)
            }
        }
    }


    private fun checkMove(row: Int, column: Int, currentFigure: AbstractFigure): Boolean {
        return (Board.gameBoard[column][row].color != currentFigure.color)
    }
}