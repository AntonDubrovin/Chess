package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.FigureColor
import com.example.mygame.figure.common.AbstractFigure

class Empty(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int = -1

    override fun showMove(canvas: Canvas, width: Int, context: Context, turn: FigureColor) {
    }

    override fun makeMove(newX: Int, newY: Int, turn: FigureColor): Boolean {
        x = newX
        y = newY
        return true
    }
}