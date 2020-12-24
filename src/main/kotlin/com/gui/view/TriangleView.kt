package com.gui.view

import com.gui.model.TriangleModel
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Pos
import tornadofx.*
import tornadofx.ValidationSeverity.*

class TriangleView : View("Triangle") {
    private val triangleModel: TriangleModel by inject()

    fun isSuccess() = isSuccessValue

    override var root = vbox {
        spacing = 10.0
        alignment = Pos.CENTER


        label(title)

        hbox {
            alignment = Pos.CENTER

            text("First side: ")
            textfield(triangleModel.sideA) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        triangleModel.sideA.value == 0.0 -> ValidationMessage(
                                "Side can't be zero",
                                Error,
                        )
                        requireTriangleNonExistence(
                                sideA = triangleModel.sideA,
                                sideB = triangleModel.sideB,
                                sideC = triangleModel.sideC,
                        ) -> ValidationMessage(
                                "The triangle with these sides does not exist",
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
            textfield(triangleModel.sideB) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        triangleModel.sideB.value == 0.0 -> ValidationMessage(
                                "Side can't be zero",
                                Error,
                        )
                        requireTriangleNonExistence(
                                sideA = triangleModel.sideA,
                                sideB = triangleModel.sideB,
                                sideC = triangleModel.sideC,
                        ) -> ValidationMessage(
                                "The triangle with these sides does not exist",
                                Error,
                        )
                        else -> null
                    }
                }
            }
        }
        hbox {
            alignment = Pos.CENTER
            text("Third side: ")
            textfield(triangleModel.sideC) {
                filterInput { it.text in symbols }
                validator {
                    return@validator when {
                        it.isNullOrEmpty() -> ValidationMessage(
                                "Required value",
                                Error,
                        )
                        triangleModel.sideC.value == 0.0 -> ValidationMessage(
                                "Side can't be zero",
                                Error,
                        )
                        requireTriangleNonExistence(
                                sideA = triangleModel.sideA,
                                sideB = triangleModel.sideB,
                                sideC = triangleModel.sideC,
                        ) -> ValidationMessage(
                                "The triangle with these sides does not exist",
                                Error,
                        )
                        else -> null
                    }
                }
            }
        }


        //i don't know if this should be some kind of task but now it works just fine
        //runAsync {
        triangleModel.sideA.onChange {
            triangleModel.run {
                validate(
                        focusFirstError = false,
                        decorateErrors = true,
                        failFast = false,
                        fields = arrayOf(
                                sideB,
                                sideC,
                        ),
                )
            }
        }

        triangleModel.sideB.onChange {
            triangleModel.run {
                validate(
                        focusFirstError = false,
                        decorateErrors = true,
                        failFast = false,
                        fields = arrayOf(
                                sideA,
                                sideC,
                        ),
                )
            }
        }
        triangleModel.sideC.onChange {
            triangleModel.run {
                validate(
                        focusFirstError = false,
                        decorateErrors = true,
                        failFast = false,
                        fields = arrayOf(
                                sideA,
                                sideB,
                        ),
                )
            }
        } //ui{ }
        //}

        buttonbar {
            button("Add") {
                isDefaultButton = true
                enableWhen(triangleModel.valid)
                action {
                    isSuccessValue = true
                    triangleModel.apply { close() }
                }
            }
        }

    }

    companion object {
        //check if triangle with these sides is non-existent
        private fun requireTriangleNonExistence(
                sideA: SimpleDoubleProperty,
                sideB: SimpleDoubleProperty,
                sideC: SimpleDoubleProperty,
        ): Boolean {
            return !((sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA))
        }

        private const val symbols = "1234567890,"
        private var isSuccessValue = false

    }

}