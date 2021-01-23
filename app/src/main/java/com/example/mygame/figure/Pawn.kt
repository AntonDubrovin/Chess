package com.example.mygame.figure

import android.graphics.Canvas
import com.example.mygame.board.Board
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.MoveMaker
import com.example.mygame.R
import com.example.mygame.figure.common.AbstractFigure

class Pawn(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override val const: Boolean = true
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int

    init {
        picture = if (color == FigureColor.WHITE) {
            R.drawable.wpawn
        } else {
            R.drawable.bpawn
        }
    }

    override fun moveCell(color: FigureColor) {
        if (color == FigureColor.WHITE && x - 1 >= 0) {
            MoveMaker.wrongMove.add(Pair(x - 1, y - 1))
        }
        if (color == FigureColor.WHITE && x + 1 <= 7) {
            MoveMaker.wrongMove.add(Pair(x + 1, y - 1))
        }
        if (color == FigureColor.BLACK && x - 1 >= 0) {
            MoveMaker.wrongMove.add(Pair(x - 1, y + 1))
        }
        if (color == FigureColor.BLACK && x + 1 <= 7) {
            MoveMaker.wrongMove.add(Pair(x + 1, y + 1))
        }
    }

    override fun showMove(canvas: Canvas, width: Int, turn: FigureColor) {
        if (turn == color) {
            if (color == FigureColor.BLACK) {
                if (y == 1) {
                    if (Board.gameBoard[y + 1][x] is Empty) {
                        show(canvas, width, y, 1, x, 0)
                        if (Board.gameBoard[y + 2][x] is Empty) {
                            show(canvas, width, y, 2, x, 0)
                        }
                    }
                } else {
                    if (Board.gameBoard[y + 1][x] is Empty) {
                        show(canvas, width, y, 1, x, 0)
                    }
                }
            } else if (color == FigureColor.WHITE) {
                if (y == 6) {
                    if (Board.gameBoard[y - 1][x] is Empty) {
                        show(canvas, width, y, -1, x, 0)
                        if (Board.gameBoard[y - 2][x] is Empty) {
                            show(canvas, width, y, -2, x, 0)
                        }
                    }
                } else {
                    if (Board.gameBoard[y - 1][x] is Empty) {
                        show(canvas, width, y, -1, x, 0)
                    }
                }
            }
            if (color == FigureColor.WHITE && x - 1 >= 0 && Board.gameBoard[y - 1][x - 1] !is Empty && Board.gameBoard[y - 1][x - 1].color != color) {
                showAttack(canvas, width, y, -1, x, -1)
            }
            if (color == FigureColor.WHITE && x + 1 <= 7 && Board.gameBoard[y - 1][x + 1] !is Empty && Board.gameBoard[y - 1][x + 1].color != color) {
                showAttack(canvas, width, y, -1, x, 1)
            }

            if (color == FigureColor.BLACK && x - 1 >= 0 && Board.gameBoard[y + 1][x - 1] !is Empty && Board.gameBoard[y + 1][x - 1].color != color) {
                showAttack(canvas, width, y, 1, x, -1)
            }
            if (color == FigureColor.BLACK && x + 1 <= 7 && Board.gameBoard[y + 1][x + 1] !is Empty && Board.gameBoard[y + 1][x + 1].color != color) {
                showAttack(canvas, width, y, 1, x, 1)
            }
        }
    }

    override fun makeMove(
            newX: Int,
            newY: Int,
            turn: FigureColor
    ): Boolean { //TODO взятие на проходе //TODO проходная пешка -> что-то
        if (turn == color) {
            if (Board.gameBoard[newY][newX] is Empty) {
                if (color == FigureColor.WHITE && newX == x && (y == 6 && (newY == y - 1 || newY == y - 2) || y != 6 && newY == y - 1)) {
                    x = newX
                    y = newY
                    return true
                } else if (color == FigureColor.BLACK && newX == x && (y == 1 && (newY == y + 1 || newY == y + 2) || y != 1 && newY == y + 1)) {
                    x = newX
                    y = newY
                    return true
                }
            } else if (Board.gameBoard[newY][newX] !is Empty && Board.gameBoard[newY][newX].color != color) {
                if (color == FigureColor.WHITE && newY == y - 1 && (newX == x - 1 || newX == x + 1)) {
                    x = newX
                    y = newY
                    return true
                } else if (color == FigureColor.BLACK && newY == y + 1 && (newX == x - 1 || newX == x + 1)) {
                    x = newX
                    y = newY
                    return true
                }
            }
        }
        return false
    }
}