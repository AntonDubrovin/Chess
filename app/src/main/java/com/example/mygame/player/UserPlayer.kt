package com.example.mygame.player

import com.example.mygame.Board
import com.example.mygame.FigureColor
import com.example.mygame.figure.common.AbstractFigure
import com.example.mygame.player.common.AbstractPlayer

class UserPlayer(override var time: Float = 300f, override var color: FigureColor) : AbstractPlayer() {

    override fun chooseFigure(row: Int, column: Int): AbstractFigure {
        return Board.gameBoard[column][row]
    }

    override fun moveFigure(): Boolean {
        TODO("Not yet implemented")
    }
}