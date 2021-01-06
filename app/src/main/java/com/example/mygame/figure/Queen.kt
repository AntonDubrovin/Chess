package com.example.mygame.figure

import android.content.Context
import android.graphics.Canvas
import com.example.mygame.figure.common.Figure
import com.example.mygame.R

class Queen(_x: Int, _y: Int, _color: String) : Figure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: String = _color
    override val picture: Int

    init {
        picture = if (color == "white") {
            R.drawable.wqueen
        } else {
            R.drawable.bqueen
        }
    }

    override fun showMove(canvas: Canvas, width: Int, context: Context) {

    }

    override fun makeMove(newX: Int, newY: Int) {
        TODO("Not yet implemented")
    }
}