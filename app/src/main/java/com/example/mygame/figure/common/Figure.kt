package com.example.mygame.figure.common

import android.content.Context
import android.graphics.*
import com.example.mygame.R

abstract class Figure : FigureInterface {
    abstract var x: Int
    abstract var y: Int
    abstract val color: String
    abstract val picture: Int

    abstract fun showMove(canvas: Canvas, width: Int, context: Context): Unit
    abstract fun makeMove(newX: Int, newY: Int): Unit
    fun draw(canvas: Canvas, width: Int, context: Context): Unit {
        val paintWhite = Paint().apply {
            color = Color.parseColor("#DEB887")
            isAntiAlias = true
            strokeWidth = 10F
            style = Paint.Style.FILL
        }

        val icon = BitmapFactory.decodeResource(
                context.resources,
                picture
        )
        canvas.drawBitmap(
                Bitmap.createScaledBitmap(icon, width / 8, width / 8, true),
                0f + x * (width / 8),
                0f + y * (width / 8),
                paintWhite
        )
    }

    fun show(canvas: Canvas, width: Int, context: Context, row: Int, column: Int) {
        val paintBlack = Paint().apply {
            color = Color.BLACK
            isAntiAlias = true
            strokeWidth = 10F
            style = Paint.Style.FILL
        }
        val paintWhite = Paint().apply {
            color = Color.parseColor("#DCDCDC")
            isAntiAlias = true
            strokeWidth = 10F
            style = Paint.Style.FILL
        }
        canvas.drawCircle(0f + column * (width / 8) + width/16,0f + row * (width / 8) + width/16, (width.toFloat() / 64),paintBlack )
        canvas.drawCircle(0f + column * (width / 8) + width/16,0f + row * (width / 8) + width/16, (width.toFloat() / 90),paintWhite )

    }
}