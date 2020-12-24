package com.gui.model

import com.gui.initial_values.TriangleValue
import tornadofx.*

class TriangleModel : ItemViewModel<TriangleValue>() {
    val sideA = bind(TriangleValue::sideA)
    val sideB = bind(TriangleValue::sideB)
    val sideC = bind(TriangleValue::sideC)
}