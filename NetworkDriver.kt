package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

object NetworkDriver {
    init {
        println("Initializing $this")
    }

    fun log(): NetworkDriver = apply { println("Network driver: $this") }
}

class SingletonTest {
    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        Assert.assertEquals(networkDriver1, NetworkDriver)
        Assert.assertEquals(networkDriver2, NetworkDriver)
    }
}