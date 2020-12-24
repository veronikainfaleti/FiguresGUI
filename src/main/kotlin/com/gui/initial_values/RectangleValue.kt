package com.gui.initial_values

import javafx.beans.property.SimpleDoubleProperty

class RectangleValue(
        sideX: Double,
        sideY: Double,
) {
    val sideX = SimpleDoubleProperty(sideX)
    val sideY = SimpleDoubleProperty(sideY)
}