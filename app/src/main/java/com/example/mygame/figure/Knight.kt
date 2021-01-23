package com.example.mygame.figure

import android.graphics.Canvas
import com.example.mygame.board.Board
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.MoveMaker
import com.example.mygame.R
import com.example.mygame.figure.common.AbstractFigure

class Knight(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override val const: Boolean = true
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int

    init {
        picture = if (color == FigureColor.WHITE) {
            R.drawable.wknight
        } else {
            R.drawable.bknight
        }
    }

    override fun moveCell(color_: FigureColor) {
        if (color_ == color) {
            if (y - 2 >= 0 && x + 1 <= 7 && Board.gameBoard[y - 2][x + 1] is Empty) {
                MoveMaker.underAttack.add(Pair(x + 1, y - 2))
            } else if (y - 2 >= 0 && x + 1 <= 7 && Board.gameBoard[y - 2][x + 1] !is Empty) {
                MoveMaker.underAttack.add(Pair(x + 1, y - 2))
            }
            if (y - 1 >= 0 && x + 2 <= 7 && Board.gameBoard[y - 1][x + 2] is Empty) {
                MoveMaker.underAttack.add(Pair(x + 2, y - 1))
            } else if (y - 1 >= 0 && x + 2 <= 7 && Board.gameBoard[y - 1][x + 2] !is Empty) {
                MoveMaker.underAttack.add(Pair(x + 2, y - 1))
            }
            if (y + 1 <= 7 && x + 2 <= 7 && Board.gameBoard[y + 1][x + 2] is Empty) {
                MoveMaker.underAttack.add(Pair(x + 2, y + 1))
            } else if (y + 1 <= 7 && x + 2 <= 7 && Board.gameBoard[y + 1][x + 2] !is Empty) {
                MoveMaker.underAttack.add(Pair(x + 2, y + 1))
            }
            if (y + 2 <= 7 && x + 1 <= 7 && Board.gameBoard[y + 2][x + 1] is Empty) {
                MoveMaker.underAttack.add(Pair(x + 1, y + 2))
            } else if (y + 2 <= 7 && x + 1 <= 7 && Board.gameBoard[y + 2][x + 1] !is Empty) {
                MoveMaker.underAttack.add(Pair(x + 1, y + 2))
            }
            if (y + 2 <= 7 && x - 1 >= 0 && Board.gameBoard[y + 2][x - 1] is Empty) {
                MoveMaker.underAttack.add(Pair(x - 1, y + 2))
            } else if (y + 2 <= 7 && x - 1 >= 0 && Board.gameBoard[y + 2][x - 1] !is Empty) {
                MoveMaker.underAttack.add(Pair(x - 1, y + 2))
            }
            if (y + 1 <= 7 && x - 2 >= 0 && Board.gameBoard[y + 1][x - 2] is Empty) {
                MoveMaker.underAttack.add(Pair(x - 2, y + 1))
            } else if (y + 1 <= 7 && x - 2 >= 0 && Board.gameBoard[y + 1][x - 2] !is Empty) {
                MoveMaker.underAttack.add(Pair(x - 2, y + 1))
            }
            if (y - 1 >= 0 && x - 2 >= 0 && Board.gameBoard[y - 1][x - 2] is Empty) {
                MoveMaker.underAttack.add(Pair(x - 2, y - 1))
            } else if (y - 1 >= 0 && x - 2 >= 0 && Board.gameBoard[y - 1][x - 2] !is Empty) {
                MoveMaker.underAttack.add(Pair(x - 2, y - 1))
            }
            if (y - 2 >= 0 && x - 1 >= 0 && Board.gameBoard[y - 2][x - 1] is Empty) {
                MoveMaker.underAttack.add(Pair(x - 1, y - 2))
            } else if (y - 2 >= 0 && x - 1 >= 0 && Board.gameBoard[y - 2][x - 1] !is Empty) {
                MoveMaker.underAttack.add(Pair(x - 1, y - 2))
            }
        }
    }

    override fun showMove(canvas: Canvas, width: Int, turn: FigureColor) {
        if (turn == color) {
            if (y - 2 >= 0 && x + 1 <= 7 && Board.gameBoard[y - 2][x + 1] is Empty) {
                show(canvas, width, y, -2, x, 1)
            } else if (y - 2 >= 0 && x + 1 <= 7 && Board.gameBoard[y - 2][x + 1] !is Empty && Board.gameBoard[y - 2][x + 1].color != color) {
                showAttack(canvas, width, y, -2, x, 1)
            }
            if (y - 1 >= 0 && x + 2 <= 7 && Board.gameBoard[y - 1][x + 2] is Empty) {
                show(canvas, width, y, -1, x, 2)
            } else if (y - 1 >= 0 && x + 2 <= 7 && Board.gameBoard[y - 1][x + 2] !is Empty && Board.gameBoard[y - 1][x + 2].color != color) {
                showAttack(canvas, width, y, -1, x, 2)
            }
            if (y + 1 <= 7 && x + 2 <= 7 && Board.gameBoard[y + 1][x + 2] is Empty) {
                show(canvas, width, y, 1, x, 2)
            } else if (y + 1 <= 7 && x + 2 <= 7 && Board.gameBoard[y + 1][x + 2] !is Empty && Board.gameBoard[y + 1][x + 2].color != color) {
                showAttack(canvas, width, y, 1, x, 2)
            }
            if (y + 2 <= 7 && x + 1 <= 7 && Board.gameBoard[y + 2][x + 1] is Empty) {
                show(canvas, width, y, 2, x, 1)
            } else if (y + 2 <= 7 && x + 1 <= 7 && Board.gameBoard[y + 2][x + 1] !is Empty && Board.gameBoard[y + 2][x + 1].color != color) {
                showAttack(canvas, width, y, 2, x, 1)
            }
            if (y + 2 <= 7 && x - 1 >= 0 && Board.gameBoard[y + 2][x - 1] is Empty) {
                show(canvas, width, y, 2, x, -1)
            } else if (y + 2 <= 7 && x - 1 >= 0 && Board.gameBoard[y + 2][x - 1] !is Empty && Board.gameBoard[y + 2][x - 1].color != color) {
                showAttack(canvas, width, y, 2, x, -1)
            }
            if (y + 1 <= 7 && x - 2 >= 0 && Board.gameBoard[y + 1][x - 2] is Empty) {
                show(canvas, width, y, 1, x, -2)
            } else if (y + 1 <= 7 && x - 2 >= 0 && Board.gameBoard[y + 1][x - 2] !is Empty && Board.gameBoard[y + 1][x - 2].color != color) {
                showAttack(canvas, width, y, 1, x, -2)
            }
            if (y - 1 >= 0 && x - 2 >= 0 && Board.gameBoard[y - 1][x - 2] is Empty) {
                show(canvas, width, y, -1, x, -2)
            } else if (y - 1 >= 0 && x - 2 >= 0 && Board.gameBoard[y - 1][x - 2] !is Empty && Board.gameBoard[y - 1][x - 2].color != color) {
                showAttack(canvas, width, y, -1, x, -2)
            }
            if (y - 2 >= 0 && x - 1 >= 0 && Board.gameBoard[y - 2][x - 1] is Empty) {
                show(canvas, width, y, -2, x, -1)
            } else if (y - 2 >= 0 && x - 1 >= 0 && Board.gameBoard[y - 2][x - 1] !is Empty && Board.gameBoard[y - 2][x - 1].color != color) {
                showAttack(canvas, width, y, -2, x, -1)
            }
        }
    }

    override fun makeMove(newX: Int, newY: Int, turn: FigureColor): Boolean {
        if (turn == color) {
            if (Board.gameBoard[newY][newX] is Empty || Board.gameBoard[newY][newX] !is Empty && Board.gameBoard[newY][newX].color != color) {
                if (newY == y - 2 && newX == x + 1 ||
                        newY == y - 1 && newX == x + 2 ||
                        newY == y + 1 && newX == x + 2 ||
                        newY == y + 2 && newX == x + 1 ||
                        newY == y + 2 && newX == x - 1 ||
                        newY == y + 1 && newX == x - 2 ||
                        newY == y - 1 && newX == x - 2 ||
                        newY == y - 2 && newX == x - 1
                ) {
                    x = newX
                    y = newY
                    return true
                }
            }
        }
        return false
    }
}