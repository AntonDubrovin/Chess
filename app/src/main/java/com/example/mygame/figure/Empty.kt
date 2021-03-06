package com.example.mygame.figure

import android.graphics.Canvas
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.figure.common.AbstractFigure

class Empty(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override val const: Boolean = true
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int = -1

    override fun moveCell(color: FigureColor) {
    }

    override fun showMove(canvas: Canvas, width: Int, turn: FigureColor) {
    }

    override fun makeMove(newX: Int, newY: Int, turn: FigureColor): Boolean {
        x = newX
        y = newY
        return true
    }
}