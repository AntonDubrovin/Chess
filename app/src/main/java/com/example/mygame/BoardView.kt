package com.example.mygame

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.example.mygame.figure.Empty
import com.example.mygame.figure.common.Figure
import kotlinx.android.synthetic.main.activity_main.view.*


@SuppressLint("ClickableViewAccessibility")
class BoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var currentFigure: Figure = Empty(0, 0, "white")
    var flag = false
    var touchX: Int = -1
    var touchY: Int = -1
    var count = 0


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
        try {
            Board.initialize()
            board.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (!flag && (event.y.toInt() / (width / 8)) <= 7) {
                            val xcord = event.x.toInt() / (width / 8)
                            val ycord = event.y.toInt() / (width / 8)

                            if (Board.gameBoard[ycord][xcord] !is Empty) {
                                touchX = xcord
                                touchY = ycord
                                currentFigure = Board.gameBoard[ycord][xcord]
                                flag = true
                                invalidate()
                            }
                        } else if (flag && (event.y.toInt() / (width / 8)) <= 7) {
                            Board.gameBoard[touchY][touchX] = Empty(touchX, touchY, "white")
                            val xcord = event.x.toInt() / (width / 8)
                            val ycord = event.y.toInt() / (width / 8)
                            Board.gameBoard[ycord][xcord] = currentFigure
                            currentFigure.makeMove(xcord, ycord)
                            touchX = -1
                            touchY = -1
                            invalidate()
                            flag = false
                        }
                    }
                }
                true
            }

        } finally {
            a.recycle()
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

    private fun drawFigure(canvas: Canvas, figure: Figure) {
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
        Toast.makeText(context, "$count", Toast.LENGTH_SHORT).show()
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
            Board.gameBoard[touchY][touchX].showMove(canvas, width, context)
        }
    }
}