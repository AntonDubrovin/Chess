package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.Board
import com.example.mygame.figure.common.Figure
import com.example.mygame.R

class King(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int

    init {
        picture = if (color == "white") {
            R.drawable.wking
        } else {
            R.drawable.bking
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context) {
        for (dx in -1..1) {
            for (dy in -1..1) {
                if (dx != 0 || dy != 0) {
                    if (x + dx in 0..7 && y + dy in 0..7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                        show(canvas, width, context, y + dy, x + dx)
                    }
                }
            }
        }
    }

    override fun makeMove(newX: Int, newY: Int): Boolean {
        if (Board.gameBoard[newY][newX] is Empty || Board.gameBoard[newY][newX] !is Empty && Board.gameBoard[newY][newX].color != color) {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (dx != 0 || dy != 0) {
                        if (x + dx == newX && y + dy == newY) {
                            x = newX
                            y = newY
                            return true
                        }
                    }
                }
            }
        }
        return false
    }
}