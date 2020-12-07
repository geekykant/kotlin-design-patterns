package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

class Calculator {
    fun sum(a: Int, b: Int) = a + b
}

class CalculatorTest {
    @Test
    fun testSum() {
        val calc = Calculator()
        Assert.assertEquals(calc.sum(3,5), 8)
    }
}

