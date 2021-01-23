package com.example.mygame.player

import com.example.mygame.MoveMaker
import com.example.mygame.board.Board
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.figure.Empty
import com.example.mygame.figure.common.AbstractFigure
import com.example.mygame.player.common.AbstractPlayer

class UserPlayer(override var time: Long = 300, override var color: FigureColor) :
        AbstractPlayer() {


    override val rock: Int = 0

    override fun chooseFigure(row: Int, column: Int): AbstractFigure {
        return if (Board.gameBoard[column][row].color == color) {
            Board.gameBoard[column][row]
        } else {
            Empty(-1, -1, FigureColor.EMPTY)
        }
    }

    override fun moveFigure(currentFigure: AbstractFigure, newX: Int, newY: Int): Boolean {
        return MoveMaker.checkChoose(currentFigure.y, newY - currentFigure.y, currentFigure.x, newX - currentFigure.x, color) &&
                currentFigure.makeMove(newX, newY, color)
    }
}