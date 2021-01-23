package com.example.mygame.figure.common

import android.content.Context
import android.graphics.*
import com.example.mygame.MoveMaker

abstract class AbstractFigure : FigureInterface {
    abstract val const: Boolean
    abstract var x: Int
    abstract var y: Int
    abstract val color: FigureColor
    abstract val picture: Int

    abstract fun moveCell(color: FigureColor)

    abstract fun showMove(canvas: Canvas, width: Int, turn: FigureColor)

    abstract fun makeMove(newX: Int, newY: Int, turn: FigureColor): Boolean
    fun draw(canvas: Canvas, width: Int, context: Context) {
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

    fun show(canvas: Canvas, width: Int, column: Int, dy: Int, row: Int, dx: Int) {
        if (MoveMaker.checkChoose(column, dy, row, dx, color)) {
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
            canvas.drawCircle(
                0f + (row + dx) * (width / 8) + width / 16,
                0f + (column + dy) * (width / 8) + width / 16,
                (width.toFloat() / 64),
                paintBlack
            )
            canvas.drawCircle(
                0f + (row + dx) * (width / 8) + width / 16,
                0f + (column + dy) * (width / 8) + width / 16,
                (width.toFloat() / 90),
                paintWhite
            )
        }
    }

    fun showAttack(canvas: Canvas, width: Int, column: Int, dy: Int, row: Int, dx: Int) {
        if (MoveMaker.checkChoose(column, dy, row, dx, color)) {
            val paintAttack = Paint().apply {
                color = Color.parseColor("#ccFF4500")
                isAntiAlias = true
                strokeWidth = 10F
                style = Paint.Style.STROKE
            }
            canvas.drawRect(
                0f + (row + dx) * (width / 8) + 1,
                0f + (column + dy) * (width / 8) + 1,
                0f + (row + dx) * (width / 8) + width / 8 - 1,
                0f + (column + dy) * (width / 8) + width / 8 - 1,
                paintAttack
            )
        }
    }
}