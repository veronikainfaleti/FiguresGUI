package com.figures

import kotlin.math.sqrt

class Triangle(
        private val sideA: Double,
        private val sideB: Double = sideA,
        private val sideC: Double = sideA,
): Shape {

    init {
        require((sideA+sideB>sideC) && (sideA+sideC>sideB) && (sideB+sideC>sideA))
        { "The triangle with sides $sideA, $sideB, $sideC does not exist." }
    }

    override fun getCalcArea(): Double {
            val p = getCalcPerimeter() / 2
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }

    override fun getCalcPerimeter(): Double = sideA+sideB+sideC

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Triangle

        if (sideA != other.sideA) return false
        if (sideB != other.sideB) return false
        if (sideC != other.sideC) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sideA.hashCode()
        result = 31 * result + sideB.hashCode()
        result = 31 * result + sideC.hashCode()
        return result
    }

    override fun toString(): String {
        return "Triangle($sideA,$sideB,$sideC)"
    }

}