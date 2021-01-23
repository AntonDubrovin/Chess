package com.example.mygame.figure

import android.graphics.Canvas
import com.example.mygame.board.Board
import com.example.mygame.figure.common.FigureColor
import com.example.mygame.MoveMaker
import com.example.mygame.R
import com.example.mygame.figure.common.AbstractFigure

class King(_x: Int, _y: Int, _color: FigureColor) : AbstractFigure() {
    override var x: Int = _x
    override var y: Int = _y
    override val color: FigureColor = _color
    override val picture: Int
    private var rock = false
    override var const = false

    init {
        picture = if (color == FigureColor.WHITE) {
            R.drawable.wking
        } else {
            R.drawable.bking
        }
    }

    override fun moveCell(color_: FigureColor) {
        if (color_ == color) {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (dx != 0 || dy != 0) {
                        if (x + dx in 0..7 && y + dy in 0..7 && Board.gameBoard[y + dy][x + dx] is Empty) {
                            MoveMaker.wrongMove.add(Pair(x + dx, y + dy))
                        } else if (x + dx in 0..7 && y + dy in 0..7) {
                            MoveMaker.wrongMove.add(Pair(x + dx, y + dy))
                        }
                    }
                }
            }
        }
    }

    override fun showMove(canvas: Canvas, width: Int, turn: FigureColor) {
        if (turn == color) {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (dx != 0 || dy != 0) {
                        if (x + dx in 0..7 && y + dy in 0..7 && Board.gameBoard[y + dy][x + dx] is Empty &&
                            !MoveMaker.wrongMove.contains(Pair(x + dx, y + dy))
                        ) {
                            show(canvas, width, y + dy, x + dx)
                        } else if (x + dx in 0..7 && y + dy in 0..7 && Board.gameBoard[y + dy][x + dx].color != color &&
                            Board.gameBoard[y + dy][x + dx] !is Empty && !MoveMaker.wrongMove.contains(
                                Pair(
                                    x + dx,
                                    y + dy
                                )
                            )
                        ) {
                            showAttack(canvas, width, y + dy, x + dx)
                        }
                    }
                }
            }
        }
        if (color == FigureColor.WHITE && y == 7 && x == 4 && !rock && !const) {
            if (Board.gameBoard[7][5] is Empty && Board.gameBoard[7][6] is Empty &&
                !MoveMaker.wrongMove.contains(Pair(5, 7)) && !MoveMaker.wrongMove.contains(
                    Pair(
                        6,
                        7
                    )
                ) && !MoveMaker.wrongMove.contains(Pair(4, 7)) &&
                Board.gameBoard[7][7] is Rook && Board.gameBoard[7][7].color == FigureColor.WHITE && Board.gameBoard[7][7].const
            ) {
                show(canvas, width, 7, 6)
            }
            if (Board.gameBoard[7][3] is Empty && Board.gameBoard[7][2] is Empty && Board.gameBoard[7][1] is Empty &&
                !MoveMaker.wrongMove.contains(Pair(3, 7)) && !MoveMaker.wrongMove.contains(
                    Pair(
                        2,
                        7
                    )
                ) && !MoveMaker.wrongMove.contains(Pair(4, 7)) && !MoveMaker.wrongMove.contains(Pair(1, 7)) &&
                Board.gameBoard[7][0] is Rook && Board.gameBoard[7][0].color == FigureColor.WHITE && Board.gameBoard[7][0].const
            ) {
                show(canvas, width, 7, 2)
            }
        } else if (color == FigureColor.BLACK && y == 0 && x == 4 && !rock && !const) {
            if (Board.gameBoard[0][5] is Empty && Board.gameBoard[0][6] is Empty &&
                !MoveMaker.wrongMove.contains(Pair(5, 0)) && !MoveMaker.wrongMove.contains(
                    Pair(
                        6,
                        0
                    )
                ) && !MoveMaker.wrongMove.contains(Pair(4, 0)) &&
                Board.gameBoard[0][7] is Rook && Board.gameBoard[0][7].color == FigureColor.BLACK && Board.gameBoard[0][7].const
            ) {
                show(canvas, width, 0, 6)
            }
            if (Board.gameBoard[0][3] is Empty && Board.gameBoard[0][2] is Empty && Board.gameBoard[0][1] is Empty &&
                !MoveMaker.wrongMove.contains(Pair(3, 0)) && !MoveMaker.wrongMove.contains(
                    Pair(
                        2,
                        0
                    )
                ) && !MoveMaker.wrongMove.contains(Pair(4, 0)) && !MoveMaker.wrongMove.contains(Pair(1, 0)) &&
                Board.gameBoard[0][0] is Rook && Board.gameBoard[0][0].color == FigureColor.BLACK && Board.gameBoard[0][0].const
            ) {
                show(canvas, width, 0, 2)
            }
        }
    }

    override fun makeMove(
        newX: Int,
        newY: Int,
        turn: FigureColor
    ): Boolean { //TODO ДОХУЯ ВСЕГО ПОМОГИТЕ
        if (turn == color) {
            if (Board.gameBoard[newY][newX] is Empty || Board.gameBoard[newY][newX] !is Empty && Board.gameBoard[newY][newX].color != color) {
                for (dx in -1..1) {
                    for (dy in -1..1) {
                        if (dx != 0 || dy != 0) {
                            if (x + dx == newX && y + dy == newY && !MoveMaker.wrongMove.contains(
                                    Pair(
                                        x + dx,
                                        y + dy
                                    )
                                )
                            ) {
                                x = newX
                                y = newY
                                const = false
                                return true
                            }
                        }
                    }
                }
            }
            if (color == FigureColor.WHITE && y == 7 && x == 4 && !rock && !const) {
                if (newX == 6 && newY == 7) {
                    if (Board.gameBoard[7][5] is Empty && Board.gameBoard[7][6] is Empty &&
                        !MoveMaker.wrongMove.contains(Pair(5, 7)) && !MoveMaker.wrongMove.contains(
                            Pair(
                                6,
                                7
                            )
                        ) && !MoveMaker.wrongMove.contains(Pair(4, 7)) &&
                        Board.gameBoard[7][7].color == FigureColor.WHITE && Board.gameBoard[7][7] is Rook && Board.gameBoard[7][7].const
                    ) {
                        x = newX
                        y = newY
                        Board.gameBoard[7][7].x = 5
                        Board.gameBoard[7][5] = Board.gameBoard[7][7]
                        Board.gameBoard[7][7] = Empty(7, 7, FigureColor.EMPTY)
                        rock = true
                        return true
                    }
                } else if (newX == 2 && newY == 7) {
                    if (Board.gameBoard[7][3] is Empty && Board.gameBoard[7][2] is Empty && Board.gameBoard[7][1] is Empty &&
                        !MoveMaker.wrongMove.contains(Pair(3, 7)) && !MoveMaker.wrongMove.contains(
                            Pair(
                                2,
                                7
                            )
                        ) && !MoveMaker.wrongMove.contains(Pair(4, 7)) && !MoveMaker.wrongMove.contains(
                            Pair(
                                1,
                                7
                            )
                        ) &&
                        Board.gameBoard[7][0].color == FigureColor.WHITE && Board.gameBoard[7][0] is Rook && Board.gameBoard[7][0].const
                    ) {
                        x = newX
                        y = newY
                        Board.gameBoard[7][0].x = 3
                        Board.gameBoard[7][3] = Board.gameBoard[7][0]
                        Board.gameBoard[7][0] = Empty(0, 7, FigureColor.EMPTY)
                        rock = true
                        return true
                    }
                }
            } else if (color == FigureColor.BLACK && y == 0 && x == 4 && !rock && !const) {
                if (newX == 6 && newY == 0) {
                    if (Board.gameBoard[0][5] is Empty && Board.gameBoard[0][6] is Empty &&
                        !MoveMaker.wrongMove.contains(Pair(5, 0)) && !MoveMaker.wrongMove.contains(
                            Pair(
                                6,
                                0
                            )
                        ) && !MoveMaker.wrongMove.contains(Pair(4, 0)) &&
                        Board.gameBoard[0][7].color == FigureColor.BLACK && Board.gameBoard[0][7] is Rook && Board.gameBoard[0][7].const
                    ) {
                        x = newX
                        y = newY
                        Board.gameBoard[0][7].x = 5
                        Board.gameBoard[0][5] = Board.gameBoard[0][7]
                        Board.gameBoard[0][7] = Empty(7, 0, FigureColor.EMPTY)
                        rock = true
                        return true
                    }
                } else if (newX == 2 && newY == 0) {
                    if (Board.gameBoard[0][3] is Empty && Board.gameBoard[0][2] is Empty && Board.gameBoard[0][1] is Empty &&
                        !MoveMaker.wrongMove.contains(Pair(3, 0)) && !MoveMaker.wrongMove.contains(
                            Pair(
                                2,
                                0
                            )
                        ) && !MoveMaker.wrongMove.contains(Pair(4, 0)) && !MoveMaker.wrongMove.contains(
                            Pair(
                                1,
                                0
                            )
                        ) &&
                        Board.gameBoard[0][0].color == FigureColor.BLACK && Board.gameBoard[0][0] is Rook && Board.gameBoard[0][0].const
                    ) {
                        x = newX
                        y = newY
                        Board.gameBoard[0][0].x = 3
                        Board.gameBoard[0][3] = Board.gameBoard[0][0]
                        Board.gameBoard[0][0] = Empty(0, 0, FigureColor.EMPTY)
                        rock = true
                        return true
                    }
                }
            }
        }
        return false
    }
}