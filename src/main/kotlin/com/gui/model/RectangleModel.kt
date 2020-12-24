package com.gui.model

import com.gui.initial_values.RectangleValue
import tornadofx.*

class RectangleModel : ItemViewModel<RectangleValue>() {
    val sideX = bind(RectangleValue::sideX)
    val sideY = bind(RectangleValue::sideY)
}