package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.Board
import com.example.mygame.figure.common.Figure
import com.example.mygame.R

class Rook(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int

    init {
        picture = if (color == "white") {
            R.drawable.wrook
        } else {
            R.drawable.brook
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context) {
        var dx = 1
        while (x + dx <= 7 && Board.gameBoard[y][x + dx] is Empty) {
            show(canvas, width, context, y, x + dx)
            dx++
        }
        dx = -1
        while (x + dx >= 0 && Board.gameBoard[y][x + dx] is Empty) {
            show(canvas, width, context, y, x + dx)
            dx--
        }
        var dy = 1
        while (y + dy <= 7 && Board.gameBoard[y + dy][x] is Empty) {
            show(canvas, width, context, y + dy, x)
            dy++
        }
        dy = -1
        while (y + dy >= 0 && Board.gameBoard[y + dy][x] is Empty) {
            show(canvas, width, context, y + dy, x)
            dy--
        }
    }

    //show(canvas, width, context, y + 1, x)
    override fun makeMove(newX: Int, newY: Int) {
        x = newX
        y = newY
    }
}