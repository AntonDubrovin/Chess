package com.example.mygame

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
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

    var currentFigure: AbstractFigure = Empty(0, 0, FigureColor.EMPTY)
    var flag = false
    var touchX: Int = -1
    var touchY: Int = -1
    var count = 0
    var turn: FigureColor = FigureColor.WHITE
    private var moveMaker: MoveMaker


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
        try {
            Board.initialize()
            board.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val newX = transpose(event.x)
                        val newY = transpose(event.y)
                        if (!flag && newY <= 7) {
                            if (moveMaker.checkChoose(newX, newY, turn)) {
                                touchX = newX
                                touchY = newY
                                currentFigure = Board.gameBoard[newY][newX]
                                flag = true
                                invalidate()
                            }
                        } else if (flag && newY <= 7) {
                            if (moveMaker.checkMove(newX, newY, currentFigure)) {
                                if (currentFigure.makeMove(newX, newY, turn)) {
                                    turn = changeTurn(turn)
                                    Board.gameBoard[touchY][touchX] =
                                        Empty(touchX, touchY, FigureColor.EMPTY)
                                    Board.gameBoard[newY][newX] = currentFigure
                                    touchX = -1
                                    touchY = -1
                                    invalidate()
                                    flag = false
                                }
                            } else {
                                touchX = newX
                                touchY = newY
                                currentFigure = Board.gameBoard[newY][newX]
                                flag = true
                                invalidate()

                            }
                        }
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

    private fun changeTurn(turn: FigureColor): FigureColor {
        return if (turn == FigureColor.WHITE) {
            FigureColor.BLACK
        } else {
            FigureColor.WHITE
        }
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
        if (touchX != -1 && touchY != -1) {
            Board.gameBoard[touchY][touchX].showMove(canvas, width, context, turn)
        }
    }
}