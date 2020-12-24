package com.gui.controller

import com.figures.Shape
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*

class ShapeListController : Controller() {

    //user selection controller
    internal fun moveDown(selected: Int) {
        if (selected != shapes.lastIndex)
            shapes[selected] = shapes[selected + 1].also { shapes[selected + 1] = shapes[selected] }
    }

    internal fun moveUp(selected: Int) {
        if (selected != 0)
            shapes[selected] = shapes[selected - 1].also { shapes[selected - 1] = shapes[selected] }
    }

    //list change controller
    internal fun remove(selected: Int) = shapes.removeAt(selected)


    internal fun add(shape: Shape) = shapes.add(shape)

    //read-write file controller
    internal fun downloadShapes(from: String) {
        filename = from
        try {
            val listFromFile = fileWriter.read(filename) as List<Shape>
            shapes.removeAll(shapes)
            shapes.addAll(listFromFile)
        } catch (e: FileSystemException) {
            e.printStackTrace()
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
        } catch (e: TypeCastException) {
            e.printStackTrace()
        }
    }

    internal fun saveShapes(to: String) {
        filename = to
        val arrayList = ArrayList<Shape>()
        arrayList.addAll(shapes)
        try {
            fileWriter.write(arrayList, filename)
        } catch (e: FileSystemException) {
            e.printStackTrace()
        }
    }

    companion object {
        private val shapes = FXCollections.observableArrayList<Shape>()!!
        fun shapes(): ObservableList<Shape> = shapes

        private lateinit var filename: String
        private val fileWriter = FileWriter()
    }

}