package com.example.mygame.board

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.MainActivity
import com.example.mygame.MoveMaker
import com.example.mygame.R
import com.example.mygame.figure.Empty
import com.example.mygame.figure.common.AbstractFigure
import kotlinx.android.synthetic.main.activity_main.view.*

@SuppressLint("ClickableViewAccessibility")
class BoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        lateinit var instance: BoardView
            private set
    }

    private var count = 0
    var turn: FigureColor = FigureColor.WHITE
    var moveMaker: MoveMaker
    var x: Int = -1
    var y: Int = -1

    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.BoardView, defStyleAttr, defStyleRes
        )
        moveMaker = MoveMaker(MainActivity.instance.whitePlayer, MainActivity.instance.blackPlayer)
        instance = this
        try {
            Board.initialize()
            board.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        println(event.x)
                        println(event.y)
                        moveMaker.makeTurn(transpose(event.x), transpose(event.y))
                    }
                }
                true
            }
        } finally {
            a.recycle()
        }
    }

    private val paintWhite = Paint().apply {
        color = Color.parseColor("#DEB887")
        isAntiAlias = true
        strokeWidth = 10F
        style = Paint.Style.FILL
    }

    private val paintBlack = Paint().apply {
        color = Color.parseColor("#8B4513")
        isAntiAlias = true
        strokeWidth = 10F
        style = Paint.Style.FILL
    }

    private val paintSelected = Paint().apply {
        color = Color.parseColor("#B3C0C0C0")
        isAntiAlias = true
        strokeWidth = 10F
        style = Paint.Style.FILL
    }

    private fun transpose(x: Float): Int {
        return x.toInt() / (width / 8)
    }

    private fun drawCell(row: Int, column: Int, canvas: Canvas, paint: Paint) {
        canvas.drawRect(
            ((row - 1) * (width / 8)).toFloat(),
            ((column - 1) * (width / 8)).toFloat(),
            (row * ((width / 8))).toFloat(),
            (column * (width / 8)).toFloat(),
            paint
        )
    }

    private fun drawSelected(row: Int, column: Int, canvas: Canvas) {
        drawCell(row, column, canvas, paintSelected)
    }

    private fun drawFigure(canvas: Canvas, figure: AbstractFigure) {
        if (figure !is Empty) {
            figure.draw(canvas, width, context)
        }
    }

    private fun drawFigures(canvas: Canvas) {
        for (i in 0..7) {
            for (j in 0..7) {
                drawFigure(canvas, Board.gameBoard[i][j])
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        count++
        for (i in 1..8) {
            for (j in 1..8) {
                if ((i + j) % 2 == 0) {
                    drawCell(i, j, canvas, paintBlack)
                } else {
                    drawCell(i, j, canvas, paintWhite)
                }
            }
        }
        if (x != -1 && y != -1) {
            drawSelected(x + 1, y + 1, canvas)
        }
        drawFigures(canvas)
        if (moveMaker.touchX != -1 && moveMaker.touchY != -1) {
            Board.gameBoard[moveMaker.touchY][moveMaker.touchX].showMove(
                canvas,
                width,
                moveMaker.turn
            )
        }
    }
}