package com.figures

import java.io.Serializable

//interface Shape for creating a figure
interface Shape: Serializable {
    fun getCalcArea(): Double //get area of the figure
    fun getCalcPerimeter(): Double //get perimeter of the figure
}