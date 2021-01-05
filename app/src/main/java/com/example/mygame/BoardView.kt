package com.example.mygame

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*


@SuppressLint("ClickableViewAccessibility")
class BoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {


    var gameBoard = arrayOf<Array<Int>>()
    var currentFigure = -1
    var flag = false

    private fun initialize() {
        for (i in 0..7) {
            var array = arrayOf<Int>()
            for (j in 0..7) {
                if (i == 6) {
                    array += 1
                    //white pawn
                } else if (i == 1) {
                    array += -1
                    //blackpawn
                } else if (i == 7) {
                    when (j) {
                        0, 7 -> {
                            array += 4// white rook
                        }
                        1, 6 -> {
                            array += 2 // white knight
                        }
                        2, 5 -> {
                            array += 3 // white bishop
                        }
                        3 -> {
                            array += 5 // white queen
                        }
                        4 -> {
                            array += 6 // white king
                        }
                    }
                } else if (i == 0) {
                    when (j) {
                        0, 7 -> {
                            array += -4 // black rook
                        }
                        1, 6 -> {
                            array += -2 // black kngiht
                        }
                        2, 5 -> {
                            array += -3 // black bishop
                        }
                        3 -> {
                            array += -5 // black queen
                        }
                        4 -> {
                            array += -6 // black king
                        }
                    }
                } else {
                    array += 0
                }
            }
            gameBoard += array
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

    init {

        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.BoardView, defStyleAttr, defStyleRes
        )
        try {
            initialize()
            board.setOnTouchListener { _, event ->
                when (event.action) {

                    MotionEvent.ACTION_DOWN -> {
                        if(!flag) {
                            var xcord = event.x.toInt() / (width / 8)
                            var ycord = event.y.toInt() / (width / 8)
                            currentFigure = gameBoard[ycord][xcord]
                            gameBoard[ycord][xcord] = 0
                            flag = true
                        } else {
                            var xcord = event.x.toInt() / (width / 8)
                            var ycord = event.y.toInt() / (width / 8)
                            gameBoard[ycord][xcord] = currentFigure
                            invalidate()
                            flag = false
                        }
                    }


                    MotionEvent.ACTION_MOVE, MotionEvent.ACTION_CANCEL -> {
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

    private fun fig(pict: Int, row: Int, column: Int, canvas: Canvas) {
        val icon = BitmapFactory.decodeResource(
            context.resources,
            pict
        )
        canvas.drawBitmap(
            Bitmap.createScaledBitmap(icon, width / 8, width / 8, true),
            0f + (column - 1) * (width / 8),
            0f + (row - 1) * (width / 8),
            paintWhite
        )
    }

    private fun drawFigure(row: Int, column: Int, canvas: Canvas, ind: Int) {
        when (ind) {
            -1 -> {
                fig(R.drawable.bpawn, row, column, canvas)
            }
            1 -> {
                fig(R.drawable.wpawn, row, column, canvas)
            }
            2 -> {
                fig(R.drawable.wknight, row, column, canvas)
            }
            -2 -> {
                fig(R.drawable.bknight, row, column, canvas)
            }
            3 -> {
                fig(R.drawable.wbishop, row, column, canvas)
            }
            -3 -> {
                fig(R.drawable.bbishop, row, column, canvas)
            }
            4 -> {
                fig(R.drawable.wrook, row, column, canvas)
            }
            -4 -> {
                fig(R.drawable.brook, row, column, canvas)
            }
            5 -> {
                fig(R.drawable.wqueen, row, column, canvas)
            }
            -5 -> {
                fig(R.drawable.bqueen, row, column, canvas)
            }
            6 -> {
                fig(R.drawable.wking, row, column, canvas)
            }
            -6 -> {
                fig(R.drawable.bking, row, column, canvas)
            }
        }
    }


    private fun drawFigures(canvas: Canvas) {
        for (i in 0..7) {
            for (j in 0..7) {
                drawFigure(i + 1, j + 1, canvas, gameBoard[i][j])
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
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
    }


}