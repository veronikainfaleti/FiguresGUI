package com.gui.view

import com.figures.*
import com.gui.controller.ShapeListController
import com.gui.model.*
import tornadofx.*
import java.io.File

class MainView : View("Figures GUI") {
    private val listController: ShapeListController by inject()

    override val root = hbox {
        listController.downloadShapes(filename)
        hbox(10) {
            menubar {
                /*menubar has 3 options:
                *   save file to original file for "Save"
                *   save file to user chosen filename file for "Save as..."
                *   load list of shapes from user chosen filename file for "Load from..."
                * */
                menu("File") {
                    item("Save") {
                        action {
                            listController.saveShapes(filename)
                            information("Saved to $filename successfully")
                        }
                    }
                    item("Save as...").setOnAction {
                        val model = SaveLoadModel()
                        val scope = Scope(model)
                        val view = find<SaveLoadView>(scope).apply { openModal(block = true) }
                        when {
                            view.isSuccess() -> {
                                val newFilename = model.filename.value
                                listController.saveShapes(newFilename)
                                information("Saved to $newFilename successfully")
                            }
                        }
                    }
                    item("Load from...").setOnAction {
                        val model = SaveLoadModel()
                        val scope = Scope(model)
                        val view = find<SaveLoadView>(scope).apply { openModal(block = true) }
                        when {
                            view.isSuccess() -> {
                                val newFilename = model.filename.value
                                if (File(newFilename).exists()) {
                                    listController.downloadShapes(newFilename)
                                } else error("No such file")
                            }
                        }
                    }
                }

            }

            //list with shapes
            val shapesListView = listview<Shape> {
                items = ShapeListController.shapes()
            }



            /*buttons "Move Down", "Move Up", "Remove" created
            * for moving shapes inside of the list
            * and also have the shortcuts such as
            *   'S' for "Move Down"
            *   'W' for "Move Up"
            *   'Delete' for "Remove"
            * */
            vbox(10) {
                hbox(10) {
                    button("Move Down") {
                        shortcut("S")
                        action {
                            if (shapesListView.selectionModel.selectedIndex != -1)
                                listController.moveDown(shapesListView.selectionModel.selectedIndex)
                        }
                    }
                    button("Move Up") {
                        shortcut("W")
                        action {
                            if (shapesListView.selectionModel.selectedIndex != -1)
                                listController.moveUp(shapesListView.selectionModel.selectedIndex)
                        }
                    }
                }
                button("Remove") {
                    shortcut("Delete")
                    action {
                        if (shapesListView.selectionModel.selectedIndex != -1)
                            listController.remove(shapesListView.selectionModel.selectedIndex)
                    }
                }

                separator()

                /*buttons for creating shapes: rectangle, circle, triangle or square
                * */
                hbox {
                    button("Create Rectangle").setOnAction {
                        val model = RectangleModel()
                        val scope = Scope(model)
                        val view = find<RectangleView>(scope).apply { openModal(block = true) }
                        when {
                            view.isSuccess() -> {
                                val sideX = model.sideX.value
                                val sideY = model.sideY.value
                                listController.add(Rectangle(
                                        sideX,
                                        sideY,
                                ))
                            }
                        }
                    }
                    button("Create Circle").setOnAction {
                        val model = CircleModel()
                        val scope = Scope(model)
                        val view = find<CircleView>(scope).apply { openModal(block = true) }
                        when {
                            view.isSuccess() -> {
                                val radius = model.r.value
                                listController.add(Circle(radius))
                            }
                        }
                    }
                }
                hbox {
                    button("Create Triangle").setOnAction {
                        val model = TriangleModel()
                        val scope = Scope(model)
                        val view = find<TriangleView>(scope).apply { openModal(block = true) }
                        when {
                            view.isSuccess() -> {
                                val sideA = model.sideA.value
                                val sideB = model.sideB.value
                                val sideC = model.sideC.value
                                listController.add(Triangle(
                                        sideA,
                                        sideB,
                                        sideC,
                                ))
                            }
                        }
                    }
                    button("Create Square").setOnAction {
                        val model = SquareModel()
                        val scope = Scope(model)
                        val view = find<SquareView>(scope).apply { openModal(block = true) }
                        when {
                            view.isSuccess() -> {
                                val side = model.sideSquare.value
                                listController.add(Square(side))
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        //filename of the original file
        private const val filename = "shapes.bin"
    }
}

