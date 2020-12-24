package com.figures

open class Rectangle(
        private val sideX: Double,
        private val sideY: Double = sideX,
) : Shape {

    init {
        require(sideX>0 && sideY>0) { "The sides must be positive." }
    }

    override fun getCalcArea(): Double = sideX*sideY
    override fun getCalcPerimeter(): Double = 2*(sideX+sideY)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        if (sideX != other.sideX) return false
        if (sideY != other.sideY) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sideX.hashCode()
        result = 31 * result + sideY.hashCode()
        return result
    }

    override fun toString(): String {
        return "Rectangle($sideX,$sideY)"
    }


}