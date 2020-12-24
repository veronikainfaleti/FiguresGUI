package com.gui.view

import com.gui.model.RectangleModel
import javafx.geometry.Pos
import tornadofx.*
import tornadofx.ValidationSeverity.*

class RectangleView : View("Rectangle") {
    private val rectangleModel: RectangleModel by inject()

    fun isSuccess() = isSuccessValue

    override val root = form {
        spacing = 10.0
        alignment = Pos.CENTER

        label(title)
        hbox {
            alignment = Pos.CENTER
            text("First side: ")
            textfield(rectangleModel.sideX) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        rectangleModel.sideX.value == 0.0 -> ValidationMessage(
                                "Side can't be zero",
                                Error,
                        )
                        else -> null
                    }
                }

            }

        }
        hbox {
            alignment = Pos.CENTER
            text("Second side: ")
            textfield(rectangleModel.sideY) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        rectangleModel.sideY.value == 0.0 -> ValidationMessage(
                                "Side can't be zero",
                                Error,
                        )
                        else -> null
                    }
                }
            }
        }
        buttonbar {
            button("Add") {
                isDefaultButton = true
                enableWhen(rectangleModel.valid)
                action {
                    isSuccessValue = true
                    rectangleModel.apply { close() }
                }
            }
        }

    }

    companion object {
        private const val symbols = "1234567890,"
        private var isSuccessValue = false
    }
}