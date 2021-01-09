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
import com.example.mygame.figure.common.MoveMaker
import com.example.mygame.R
import com.example.mygame.figure.Empty
import com.example.mygame.figure.Rook
import com.example.mygame.figure.common.AbstractFigure
import kotlinx.android.synthetic.main.activity_main.view.*

@SuppressLint("ClickableViewAccessibility")
class BoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var currentFigure: AbstractFigure = Empty(0, 0, FigureColor.EMPTY)
    var flag = false
    var touchX: Int = -1
    var touchY: Int = -1
    var count = 0
    var turn: FigureColor = FigureColor.WHITE
    private var moveMaker: MoveMaker


    companion object {
        lateinit var instance: BoardView
            private set
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
                        moveMaker.makeTurn(transpose(event.x), transpose(event.y))
                    }
                }
                true
            }
        } finally {
            a.recycle()
        }
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
        println(
            Board.gameBoard[7][3] is Empty && Board.gameBoard[7][2] is Empty && Board.gameBoard[7][1] is Empty &&
                    Board.gameBoard[7][0].color == FigureColor.WHITE && Board.gameBoard[7][0] is Rook && Board.gameBoard[7][0].const
        )

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
        drawFigures(canvas)
        if (moveMaker.touchX != -1 && moveMaker.touchY != -1) {
            Board.gameBoard[moveMaker.touchY][moveMaker.touchX].showMove(
                canvas,
                width,
                context,
                moveMaker.turn
            )
        }
    }
}