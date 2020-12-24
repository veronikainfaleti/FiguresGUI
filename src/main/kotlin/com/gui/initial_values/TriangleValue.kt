package com.gui.initial_values

import javafx.beans.property.SimpleDoubleProperty

class TriangleValue(
        sideA: Double,
        sideB: Double,
        sideC: Double,
) {
    val sideA = SimpleDoubleProperty(sideA)
    val sideB = SimpleDoubleProperty(sideB)
    val sideC = SimpleDoubleProperty(sideC)
}

