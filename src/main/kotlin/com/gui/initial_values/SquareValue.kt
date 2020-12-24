package com.gui.initial_values

import javafx.beans.property.SimpleDoubleProperty

class SquareValue(squareSide: Double) {
    val sideSquare = SimpleDoubleProperty(squareSide)
}