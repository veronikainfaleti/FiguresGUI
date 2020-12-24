package com.figures

import kotlin.math.PI

class Circle(private val r: Double) : Shape {

    init {
        require(r > 0) { "The circle radius must be positive." }
    }

    override fun getCalcArea() = PI * r * r
    override fun getCalcPerimeter() = 2 * PI * r

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Circle

        if (r != other.r) return false

        return true
    }

    override fun hashCode(): Int {
        return r.hashCode()
    }

    override fun toString(): String {
        return "Circle($r)"
    }

}