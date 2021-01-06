package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
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
    }

    override fun makeMove(newX: Int, newY: Int) {
        x = newX
        y = newY
    }
}