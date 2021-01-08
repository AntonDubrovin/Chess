package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.Board
import com.example.mygame.FigureColor
import com.example.mygame.R
import com.example.mygame.figure.common.AbstractFigure

class King(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int

    init {
        picture = if (color == FigureColor.WHITE) {
            R.drawable.wking
        } else {
            R.drawable.bking
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context, turn: FigureColor) {
        if (turn == color) {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (dx != 0 || dy != 0) {
                        if (x + dx in 0..7 && y + dy in 0..7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                            show(canvas, width, context, y + dy, x + dx)
                        } else if (x + dx in 0..7 && y + dy in 0..7 && Board.gameBoard[y + dy][x + dx].color != color) {
                            showAttack(canvas, width, context, y + dy, x + dx)
                        }
                    }
                }
            }
        }
    }

    override fun makeMove(newX: Int, newY: Int, turn: FigureColor): Boolean { //TODO ДОХУЯ ВСЕГО ПОМОГИТЕ
        if (turn == color) {
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
        }
        return false
    }
}