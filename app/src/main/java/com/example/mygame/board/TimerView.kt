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
import com.example.mygame.R
import com.example.mygame.figure.common.FigureColor
import kotlinx.android.synthetic.main.activity_main.view.*

@SuppressLint("ClickableViewAccessibility")
class TimerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0,
) : View(context, attrs, defStyleAttr, defStyleRes) {
    companion object {
        lateinit var instance: TimerView
            private set
    }

    var timeStart = 0L
    var currentTime = 0L
    private lateinit var timerColor: FigureColor
    var timeThis = 300L

    init {
        timeStart = System.currentTimeMillis()
        val a: TypedArray = context.obtainStyledAttributes(
                attrs, R.styleable.TimerView, defStyleAttr, defStyleRes
        )
        instance = this
        try {
            val t = a.getString(R.styleable.TimerView_timerColor)
            when (t) {
                "white" -> {
                    timerColor = FigureColor.WHITE
                }
                "black" -> {
                    timerColor = FigureColor.BLACK
                }
            }

        } finally {
            a.recycle()
        }
    }

    private val paintWhite = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        strokeWidth = 2.0F
        style = Paint.Style.FILL_AND_STROKE
        textSize = 100F
        textAlign = Paint.Align.RIGHT
    }

    private fun drawTime(dTime: Long, canvas: Canvas) {
        val minutes = (dTime / 60).toString()
        var seconds = (dTime % 60).toString()
        if (seconds.length == 1) {
            seconds = "0$seconds"
        }
        if (timerColor == FigureColor.BLACK) {
            canvas.drawText("$minutes:$seconds", width.toFloat() - 10, height.toFloat() - 10, paintWhite)
        } else {
            canvas.drawText("$minutes:$seconds", width.toFloat() - 10, paintWhite.textSize - 20, paintWhite)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        currentTime = System.currentTimeMillis()

        when (BoardView.instance.moveMaker.turn) {
            timerColor -> {
                if (currentTime - timeStart > 1000) {
                    timeThis -= 1
                    timeStart = currentTime
                }
                drawTime(timeThis, canvas)
            }
            else -> {
                drawTime(timeThis, canvas)
                timeStart = System.currentTimeMillis()
            }
        }
        invalidate()
    }

}