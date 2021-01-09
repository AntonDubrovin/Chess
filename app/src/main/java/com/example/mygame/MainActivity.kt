package com.example.mygame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.player.UserPlayer
import com.example.mygame.player.common.AbstractPlayer

class MainActivity : AppCompatActivity() {

    lateinit var whitePlayer: AbstractPlayer
    lateinit var blackPlayer: AbstractPlayer

    companion object {
        lateinit var instance: MainActivity
            private set
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        whitePlayer = UserPlayer(color = FigureColor.WHITE)
        blackPlayer = UserPlayer(color = FigureColor.BLACK)
        setContentView(R.layout.activity_main)
    }


}