package com.example.mygame.player.common

import com.example.mygame.figure.common.FigureColor

abstract class AbstractPlayer : PlayerInterface {
    abstract var time: Float
    abstract var color: FigureColor
    abstract val rock: Int
}