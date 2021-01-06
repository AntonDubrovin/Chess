package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.Board
import com.example.mygame.figure.common.Figure
import com.example.mygame.R

class Knight(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int

    init {
        picture = if (color == "white") {
            R.drawable.wknight
        } else {
            R.drawable.bknight
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context) {
        if (y - 2 >= 0 && x + 1 <= 7 && Board.gameBoard[y - 2][x + 1] is Empty) {
            show(canvas, width, context, y - 2, x + 1)
        }
        if (y - 1 <= 7 && x + 2 <= 7 && Board.gameBoard[y - 1][x + 2] is Empty) {
            show(canvas, width, context, y - 1, x + 2)
        }
        if (y + 1 <= 7 && x + 2 <= 7 && Board.gameBoard[y + 1][x + 2] is Empty) {
            show(canvas, width, context, y + 1, x + 2)
        }
        if (y + 2 <= 7 && x + 1 <= 7 && Board.gameBoard[y + 2][x + 1] is Empty) {
            show(canvas, width, context, y + 2, x + 1)
        }
        if (y + 2 <= 7 && x - 1 >= 0 && Board.gameBoard[y + 2][x - 1] is Empty) {
            show(canvas, width, context, y + 2, x - 1)
        }
        if (y + 1 <= 7 && x - 2 >= 0 && Board.gameBoard[y + 1][x - 2] is Empty) {
            show(canvas, width, context, y + 1, x - 2)
        }
        if (y - 1 >= 0 && x - 2 >= 0 && Board.gameBoard[y - 1][x - 2] is Empty) {
            show(canvas, width, context, y - 1, x - 2)
        }
        if (y - 2 >= 0 && x - 1 >= 0 && Board.gameBoard[y - 2][x - 1] is Empty) {
            show(canvas, width, context, y - 2, x - 1)
        }
    }

    override fun makeMove(newX: Int, newY: Int) {
        x = newX
        y = newY
    }
}