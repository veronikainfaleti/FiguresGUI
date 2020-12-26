package com.gui

import com.gui.controller.ShapeListController
import com.gui.view.MainView
import javafx.stage.Stage
import tornadofx.*

class FiguresGUI : App(MainView::class) {
    private val listController: ShapeListController by inject()

    override fun start(stage: Stage) {
        super.start(stage)
        listController.downloadShapes(filename)
    }

    override fun stop() {
        listController.saveShapes(filename)
        super.stop()
    }

    companion object {
        private const val filename = "shapes.bin"
    }
}