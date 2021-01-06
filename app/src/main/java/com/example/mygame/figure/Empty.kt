package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.figure.common.Figure

class Empty(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int = -1

    override fun showMove(canvas: Canvas, width: Int, context: Context) {
    }

    override fun makeMove(newX: Int, newY: Int) {
        TODO("Not yet implemented")
    }
}