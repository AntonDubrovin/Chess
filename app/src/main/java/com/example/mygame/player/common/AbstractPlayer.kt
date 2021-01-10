package com.example.mygame.player.common

import com.example.mygame.figure.common.FigureColor

abstract class AbstractPlayer : PlayerInterface {
    abstract var time: Long
    abstract var color: FigureColor
    abstract val rock: Int
}