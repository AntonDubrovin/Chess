package com.example.mygame

import android.widget.Toast
import com.example.mygame.board.Board
import com.example.mygame.board.BoardView
import com.example.mygame.figure.Empty
import com.example.mygame.figure.King
import com.example.mygame.figure.common.AbstractFigure
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.player.common.AbstractPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MoveMaker(_white: AbstractPlayer, _black: AbstractPlayer) {
    companion object {

        var isGame = true

        var countBlack = 0
        var countWhite = 0

        var countColor = FigureColor.WHITE
        var isDraw = true

        var underAttack: MutableSet<Pair<Int, Int>> = mutableSetOf()

        fun checkChoose(column: Int, dy: Int, row: Int, dx: Int, color: FigureColor): Boolean {
            val copy = underAttack
            val cur = Board.gameBoard[column + dy][row + dx]
            Board.gameBoard[column + dy][row + dx] = Board.gameBoard[column][row]
            Board.gameBoard[column][row] = Empty(row, column, FigureColor.EMPTY)
            underAttack = mutableSetOf()
            when (color) {
                FigureColor.WHITE -> {
                    findAttack(FigureColor.BLACK)
                }
                FigureColor.BLACK -> {
                    findAttack(FigureColor.WHITE)
                }
            }
            for (i in underAttack) {
                if (Board.gameBoard[i.second][i.first] is King && Board.gameBoard[i.second][i.first].color == color) {
                    Board.gameBoard[column][row] = Board.gameBoard[column + dy][row + dx]
                    Board.gameBoard[column + dy][row + dx] = cur
                    BoardView.instance.invalidate()
                    underAttack = copy
                    return false
                }
            }
            Board.gameBoard[column][row] = Board.gameBoard[column + dy][row + dx]
            Board.gameBoard[column + dy][row + dx] = cur
            BoardView.instance.invalidate()
            underAttack = copy
            return true
        }

        private fun findAttack(color: FigureColor) {
            for (i in 0..7) {
                for (j in 0..7) {
                    if (Board.gameBoard[i][j] !is Empty && Board.gameBoard[i][j].color == color) {
                        Board.gameBoard[i][j].moveCell(color)
                    }
                }
            }
        }
    }


    val white: AbstractPlayer = _white
    val black: AbstractPlayer = _black
    var touchX: Int = -1
    var touchY: Int = -1
    private var currentFigure: AbstractFigure = Empty(-1, -1, FigureColor.EMPTY)
    var turn = FigureColor.WHITE
    private var flag = false


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
            BoardView.instance.x = touchX
            BoardView.instance.y = touchY
            BoardView.instance.invalidate()
        }
    }

    private fun moveFigureCommon(column: Int, row: Int) {
        Board.gameBoard[touchY][touchX] =
            Empty(touchX, touchY, FigureColor.EMPTY)
        Board.gameBoard[column][row] = currentFigure
        touchX = -1
        touchY = -1
        BoardView.instance.x = -1
        BoardView.instance.y = -1
        flag = false
        underAttack = mutableSetOf()
        checkCheck(turn)
        changeTurn(turn)
        BoardView.instance.invalidate()
    }

    private fun moveFigure(row: Int, column: Int) {
        if (checkMove(row, column, currentFigure)) {
            when (turn) {
                FigureColor.WHITE -> {
                    if (white.moveFigure(currentFigure, row, column)) {
                        moveFigureCommon(column, row)
                    }
                }
                FigureColor.BLACK -> {
                    if (black.moveFigure(currentFigure, row, column)) {
                        moveFigureCommon(column, row)
                    }

                }
                else -> {
                }
            }
        } else {
            touchX = row
            touchY = column
            BoardView.instance.x = touchX
            BoardView.instance.y = touchY
            currentFigure = Board.gameBoard[column][row]
            flag = true
            BoardView.instance.invalidate()
        }
    }

    private fun changeTurn(turn: FigureColor) {

        countBlack = 0
        countWhite = 0
        if (turn == FigureColor.WHITE) {
            this.turn = FigureColor.BLACK
        } else {
            this.turn = FigureColor.WHITE
        }
        isDraw = false
        countColor = this.turn
        for (i in 0..7) {
            for (j in 0..7) {
                if (Board.gameBoard[i][j] !is Empty && Board.gameBoard[i][j].color == this.turn) {
                    Board.gameBoard[i][j].showMove(BoardView.instance.canv, 0, this.turn)
                }
            }
        }

        if (this.turn == FigureColor.WHITE) {
            if (countWhite == 0) {
                if(BoardView.instance.check) {
                    Toast.makeText(MainActivity.instance.baseContext,"~BLACK WINS~",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(BoardView.instance.context,"~DRAW~",Toast.LENGTH_LONG).show()
                }
                isGame = false
                MainActivity.instance.stopGame()
            }
        } else if(this.turn == FigureColor.BLACK){
            if(countBlack == 0){
                if(BoardView.instance.check){
                    Toast.makeText(BoardView.instance.context,"~WHITE WINS~",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(BoardView.instance.context,"~DRAW~",Toast.LENGTH_LONG).show()
                }
                isGame = false
                MainActivity.instance.stopGame()
            }
        }
        isDraw = true
    }

    private fun notTurn(turn: FigureColor): FigureColor {
        return when (turn) {
            FigureColor.WHITE -> {
                FigureColor.BLACK
            }
            FigureColor.BLACK -> {
                FigureColor.WHITE
            }
            else -> {
                turn
            }
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

    private fun checkCheck(color: FigureColor) {
        BoardView.instance.check = false
        findAttack(color)
        for (i in underAttack) {
            if (Board.gameBoard[i.second][i.first] is King && Board.gameBoard[i.second][i.first].color != color) {
                BoardView.instance.check = true
                BoardView.instance.invalidate()
            }
        }
        underAttack = mutableSetOf()
    }

    private fun checkMove(row: Int, column: Int, currentFigure: AbstractFigure): Boolean {
        return (Board.gameBoard[column][row].color != currentFigure.color)
    }
}