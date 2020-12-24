package com.gui.model

import com.gui.initial_values.SquareValue
import tornadofx.*

class SquareModel : ItemViewModel<SquareValue>() {
    val sideSquare = bind(SquareValue::sideSquare)
}