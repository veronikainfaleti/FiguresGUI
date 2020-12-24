package com.gui.model

import com.gui.initial_values.CircleValue
import tornadofx.*

class CircleModel : ItemViewModel<CircleValue>() {
    val r = bind(CircleValue::radius)
}