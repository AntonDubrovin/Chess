package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.mygame.Board
import com.example.mygame.figure.common.Figure
import com.example.mygame.R

class Pawn(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int

    init {
        picture = if (color == "white") {
            R.drawable.wpawn
        } else {
            R.drawable.bpawn
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context) {
        if (color == "black") {
            if (y == 1) {
                if (Board.gameBoard[y + 1][x] is Empty) {
                    show(canvas, width, context, y + 1, x)
                    if (Board.gameBoard[y + 2][x] is Empty) {
                        show(canvas, width, context, y + 2, x)
                    }
                }
            } else {
                if (Board.gameBoard[y + 1][x] is Empty) {
                    show(canvas, width, context, y + 1, x)
                }
            }
        } else if (color == "white") {
            if (y == 6) {
                if (Board.gameBoard[y - 1][x] is Empty) {
                    show(canvas, width, context, y - 1, x)
                    if (Board.gameBoard[y - 2][x] is Empty) {
                        show(canvas, width, context, y - 2, x)
                    }
                }
            } else {
                if (Board.gameBoard[y - 1][x] is Empty) {
                    show(canvas, width, context, y - 1, x)
                }
            }
        }
        if (color == "white" && x - 1 >= 0 && Board.gameBoard[y - 1][x - 1] !is Empty && Board.gameBoard[y - 1][x - 1].color != color) {
            showAttack(canvas, width, context, y - 1, x - 1)
        }
        if (color == "white" && x + 1 <= 7 && Board.gameBoard[y - 1][x + 1] !is Empty && Board.gameBoard[y - 1][x + 1].color != color) {
            showAttack(canvas, width, context, y - 1, x + 1)
        }

        if (color == "black" && x - 1 >= 0 && Board.gameBoard[y + 1][x - 1] !is Empty && Board.gameBoard[y + 1][x - 1].color != color) {
            showAttack(canvas, width, context, y + 1, x - 1)
        }
        if (color == "black" && x + 1 <= 7 && Board.gameBoard[y + 1][x + 1] !is Empty && Board.gameBoard[y + 1][x + 1].color != color) {
            showAttack(canvas, width, context, y + 1, x + 1)
        }
    }

    override fun makeMove(
        newX: Int,
        newY: Int
    ): Boolean { //TODO взятие на проходе //TODO проходная пешка -> что-то
        if (Board.gameBoard[newY][newX] is Empty) {
            if (color == "white" && newX == x && (y == 6 && (newY == y - 1 || newY == y - 2) || y != 6 && newY == y - 1)) {
                x = newX
                y = newY
                return true
            } else if (color == "black" && newX == x && (y == 1 && (newY == y + 1 || newY == y + 2) || y != 1 && newY == y + 1)) {
                x = newX
                y = newY
                return true
            }
        } else if (Board.gameBoard[newY][newX] !is Empty && Board.gameBoard[newY][newX].color != color) {
            if (color == "white" && newY == y - 1 && (newX == x - 1 || newX == x + 1)) {
                x = newX
                y = newY
                return true
            } else if (color == "black" && newY == y + 1 && (newX == x - 1 || newX == x + 1)) {
                x = newX
                y = newY
                return true
            }
        }
        return false
    }
}