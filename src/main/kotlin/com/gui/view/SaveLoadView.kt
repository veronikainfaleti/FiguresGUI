package com.gui.view

import com.gui.model.SaveLoadModel
import tornadofx.*
import tornadofx.ValidationSeverity.*

class SaveLoadView : View("Filename") {
    private val saveLoadModel: SaveLoadModel by inject()
    private var isSuccessValue = false
    fun isSuccess() = isSuccessValue
    override val root = form {
        label("Write filename: ")
        hbox {
            textfield(saveLoadModel.filename).validator {
                return@validator when {
                    it.isNullOrEmpty() -> ValidationMessage(
                            "Filename must not be empty",
                            Error,
                    )
                    else -> null
                }
            }
            button("OK") {
                isDefaultButton = true
                enableWhen(saveLoadModel.valid)
                action {
                    isSuccessValue = true
                    saveLoadModel.apply { close() }
                }
            }
        }
    }
}
