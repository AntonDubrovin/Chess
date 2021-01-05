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
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

@SuppressLint("ClickableViewAccessibility")
class BoardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {


    private val paintEven = Paint().apply {
        color = Color.parseColor("#7B68EE")
        isAntiAlias = true
        strokeWidth = 10F
        style = Paint.Style.FILL
    }

    private val paintOdd = Paint().apply {
        color = Color.parseColor("#B0E0E6")
        isAntiAlias = true
        strokeWidth = 10F
        style = Paint.Style.FILL
    }


    init {

        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.BoardView, defStyleAttr, defStyleRes
        )
        try {
            board.setOnTouchListener { _, event ->
                when (event.action) {

                    MotionEvent.ACTION_DOWN -> {
                        var xcord = event.x
                        var ycord = event.y
                        Toast.makeText(context, "$xcord + $ycord", Toast.LENGTH_SHORT).show()
                    }


                    MotionEvent.ACTION_MOVE -> {
                    }

                    MotionEvent.ACTION_UP -> {
                        var xcord = event.x
                        var ycord = event.y
                        Toast.makeText(context, "$xcord + $ycord", Toast.LENGTH_SHORT).show()
                    }

                    MotionEvent.ACTION_CANCEL -> {

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
        println((width / 8).toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        for (i in 1..8) {
            for (j in 1..8) {
                if ((i + j) % 2 == 0) {
                    drawCell(i, j, canvas, paintOdd);
                } else {
                    drawCell(i, j, canvas, paintEven);
                }
            }
        }
    }


}