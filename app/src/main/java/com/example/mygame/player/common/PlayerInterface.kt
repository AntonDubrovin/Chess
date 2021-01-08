package com.example.mygame.player.common

import com.example.mygame.figure.common.AbstractFigure

interface PlayerInterface {
    fun chooseFigure(row: Int, column: Int): AbstractFigure
    fun moveFigure(): Boolean
}