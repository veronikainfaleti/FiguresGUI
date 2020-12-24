package com.gui.view

import com.gui.model.SquareModel
import javafx.geometry.Pos
import tornadofx.*
import tornadofx.ValidationSeverity.*

class SquareView : View("Square") {
    private val squareModel: SquareModel by inject()

    fun isSuccess() = isSuccessValue

    override val root = vbox {
        spacing = 10.0
        alignment = Pos.CENTER

        label(title)
        hbox {
            alignment = Pos.CENTER
            text("Side: ")
            textfield(squareModel.sideSquare) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        squareModel.sideSquare.value == 0.0 -> ValidationMessage(
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
                enableWhen(squareModel.valid)
                action {
                    isSuccessValue = true
                    squareModel.apply { close() }
                }
            }
        }

    }

    companion object {
        private const val symbols = "1234567890,"
        private var isSuccessValue = false
    }
}