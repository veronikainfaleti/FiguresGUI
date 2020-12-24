package com.gui.view

import com.gui.model.CircleModel
import javafx.geometry.Pos
import tornadofx.*
import tornadofx.ValidationSeverity.*

class CircleView : View("Circle") {
    private val circleModel: CircleModel by inject()

    fun isSuccess() = isSuccessValue

    override val root = form {
        spacing = 10.0
        alignment = Pos.CENTER

        label(title)
        hbox {
            alignment = Pos.CENTER
            text("Radius: ")
            textfield(circleModel.r) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        circleModel.r.value == 0.0 -> ValidationMessage(
                                "Radius can't be zero",
                                Error,
                        )
                        else -> null
                    }
                }
            }

            button("Add") {
                isDefaultButton = true
                enableWhen(circleModel.valid)
                action {
                    isSuccessValue = true
                    circleModel.apply { close() }
                }
            }
        }
    }

    companion object {
        private const val symbols = "1234567890,"
        private var isSuccessValue = false
    }
}
