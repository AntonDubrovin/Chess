package com.example.mygame.player.common

import com.example.mygame.FigureColor

abstract class AbstractPlayer : PlayerInterface {
    abstract var time : Float
    abstract var color : FigureColor
}