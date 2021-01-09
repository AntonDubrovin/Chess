package com.example.mygame.player

import com.example.mygame.board.Board
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.figure.Empty
import com.example.mygame.figure.common.AbstractFigure
import com.example.mygame.player.common.AbstractPlayer

class UserPlayer(override var time: Float = 300f, override var color: FigureColor) :
    AbstractPlayer() {

    override val rock: Int = 0

    override fun chooseFigure(row: Int, column: Int): AbstractFigure {
        if (Board.gameBoard[column][row].color == color) {
            return Board.gameBoard[column][row]
        } else {
            return Empty(-1, -1, FigureColor.EMPTY)
        }
    }

    override fun moveFigure(currentFigure: AbstractFigure, newX: Int, newY: Int): Boolean {
        return currentFigure.makeMove(newX, newY, color)
    }
}