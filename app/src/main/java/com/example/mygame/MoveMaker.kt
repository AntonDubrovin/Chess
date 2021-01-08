package com.example.mygame

import com.example.mygame.figure.Empty
import com.example.mygame.figure.common.AbstractFigure
import com.example.mygame.player.common.AbstractPlayer

class MoveMaker(_white: AbstractPlayer, _black: AbstractPlayer) {

    val white: AbstractPlayer = _white
    val black: AbstractPlayer = _black

    var touchX: Int = -1
    var touchY: Int = -1

    var turn = FigureColor.WHITE

    fun choose(row: Int, column: Int, turnColor: FigureColor): Boolean {
        when (turnColor) {
            FigureColor.WHITE -> {
                val currentFigure = white.chooseFigure(row, column)
                touchX = row
                touchY = column
            }
            FigureColor.BLACK -> {

            }
            else -> {

            }
        }


        return true
    }

    fun checkChoose(row: Int, column: Int, turnColor: FigureColor): Boolean {
        return (Board.gameBoard[column][row] !is Empty && turnColor == Board.gameBoard[column][row].color)
    }

    fun checkMove(row: Int, column: Int, currentFigure: AbstractFigure): Boolean {
        return (Board.gameBoard[column][row].color != currentFigure.color)
    }
}