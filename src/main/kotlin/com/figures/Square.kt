package com.figures

class Square(private val squareSide: Double,) : Rectangle(squareSide) {
    override fun toString(): String {
        return "Square($squareSide)"
    }
}