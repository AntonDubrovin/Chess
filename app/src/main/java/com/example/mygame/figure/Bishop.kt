package com.example.mygame.figure

import android.graphics.Canvas
import com.example.mygame.board.Board
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.MoveMaker
import com.example.mygame.R
import com.example.mygame.figure.common.AbstractFigure

class Bishop(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override val const: Boolean = true
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int

    init {
        picture = if (color == FigureColor.WHITE) {
            R.drawable.wbishop
        } else {
            R.drawable.bbishop
        }
    }

    override fun moveCell(color_: FigureColor) {
        if (color_ == color) {
            var dx = 1
            var dy = 1
            while (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
                dx++
                dy++
            }
            if (x + dx <= 7 && y + dy <= 7) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
            }
            dx = -1
            dy = -1
            while (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
                dx--
                dy--
            }
            if (x + dx >= 0 && y + dy >= 0) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
            }
            dx = -1
            dy = 1
            while (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
                dy++
                dx--
            }
            if (y + dy <= 7 && x + dx >= 0) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
            }
            dx = 1
            dy = -1
            while (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
                dy--
                dx++
            }
            if (y + dy >= 0 && x + dx <= 7) {
                MoveMaker.underAttack.add(Pair(x + dx, y + dy))
            }
        }
    }

    override fun showMove(canvas: Canvas, width: Int, turn: FigureColor) {
        if (turn == color) {
            var dx = 1
            var dy = 1
            while (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                show(canvas, width, y, dy, x, dx)
                dx++
                dy++
            }
            if (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[y + dy][x + dx].color != color) {
                showAttack(canvas, width, y, dy, x, dx)
            }
            dx = -1
            dy = -1
            while (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
                show(canvas, width, y, dy, x, dx)
                dx--
                dy--
            }
            if (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[y + dy][x + dx].color != color) {
                showAttack(canvas, width, y, dy, x, dx)
            }
            dx = -1
            dy = 1
            while (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
                show(canvas, width, y, dy, x, dx)
                dy++
                dx--
            }
            if (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[y + dy][x + dx].color != color) {
                showAttack(canvas, width, y, dy, x, dx)
            }
            dx = 1
            dy = -1
            while (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                show(canvas, width, y, dy, x, dx)
                dy--
                dx++
            }
            if (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[y + dy][x + dx].color != color) {
                showAttack(canvas, width, y, dy, x, dx)
            }
        }
    }

    override fun makeMove(newX: Int, newY: Int, turn: FigureColor): Boolean {
        if (turn == color) {
            if (Board.gameBoard[newY][newX] is Empty || Board.gameBoard[newY][newX] !is Empty && Board.gameBoard[newY][newX].color != color) {
                var dx = 1
                var dy = 1
                while (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                    dx++
                    dy++
                }
                if (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[newY][newX].color != color) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                }
                dx = -1
                dy = -1
                while (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                    dx--
                    dy--
                }
                if (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[newY][newX].color != color) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                }
                dx = -1
                dy = 1
                while (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                    dy++
                    dx--
                }
                if (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[newY][newX].color != color) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                }
                dx = 1
                dy = -1
                while (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                    dy--
                    dx++
                }
                if (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[newY][newX].color != color) {
                    if (x + dx == newX && y + dy == newY) {
                        x = newX
                        y = newY
                        return true
                    }
                }
            }
        }
        return false
    }
}