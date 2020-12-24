package com.gui.model

import com.gui.initial_values.SaveLoadValue
import tornadofx.*

class SaveLoadModel : ItemViewModel<SaveLoadValue>() {
    val filename = bind(SaveLoadValue::filename)
}