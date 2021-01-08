package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.Board
import com.example.mygame.figure.common.Figure
import com.example.mygame.R

class Bishop(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int

    init {
        picture = if (color == "white") {
            R.drawable.wbishop
        } else {
            R.drawable.bbishop
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context) {
        var dx = 1
        var dy = 1
        while (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
            show(canvas, width, context, y + dy, x + dx)
            dx++
            dy++
        }
        if (x + dx <= 7 && y + dy <= 7 && Board.gameBoard[y + dy][x + dx].color != color) {
            showAttack(canvas, width, context, y + dy, x + dx)
        }
        dx = -1
        dy = -1
        while (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
            show(canvas, width, context, y + dy, x + dx)
            dx--
            dy--
        }
        if (x + dx >= 0 && y + dy >= 0 && Board.gameBoard[y + dy][x + dx].color != color) {
            showAttack(canvas, width, context, y + dy, x + dx)
        }
        dx = -1
        dy = 1
        while (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[y + dy][x + dx] is Empty) {
            show(canvas, width, context, y + dy, x + dx)
            dy++
            dx--
        }
        if (y + dy <= 7 && x + dx >= 0 && Board.gameBoard[y + dy][x + dx].color != color) {
            showAttack(canvas, width, context, y + dy, x + dx)
        }
        dx = 1
        dy = -1
        while (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[y + dy][x + dx] is Empty) {
            show(canvas, width, context, y + dy, x + dx)
            dy--
            dx++
        }
        if (y + dy >= 0 && x + dx <= 7 && Board.gameBoard[y + dy][x + dx].color != color) {
            showAttack(canvas, width, context, y + dy, x + dx)
        }
    }

    override fun makeMove(newX: Int, newY: Int): Boolean {
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
        return false
    }
}