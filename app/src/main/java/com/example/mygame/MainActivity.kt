package com.example.mygame

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.player.UserPlayer
import com.example.mygame.player.common.AbstractPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var whitePlayer: AbstractPlayer
    lateinit var blackPlayer: AbstractPlayer

    companion object {

        lateinit var instance: MainActivity
            private set
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        whitePlayer = UserPlayer(color = FigureColor.WHITE)
        blackPlayer = UserPlayer(color = FigureColor.BLACK)
        setContentView(R.layout.activity_main)

        val par: ViewGroup.LayoutParams = board.layoutParams
        par.height = 1080
        board.layoutParams = par
    }

}