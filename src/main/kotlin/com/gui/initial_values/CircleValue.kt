package com.gui.initial_values

import javafx.beans.property.SimpleDoubleProperty

class CircleValue(r: Double) {
    val radius = SimpleDoubleProperty(r)
}