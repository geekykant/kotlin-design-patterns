package com.paavam.learningcurve

import org.junit.Test
import kotlin.math.PI

interface Image {
    fun display()
}

class RealImage(private val filename: String) : Image {
    override fun display() {
        println("RealImage: Displaying $filename")
    }

    private fun loadFromDisk() {
        println("RealImage: Loading $filename")
    }

    init {
        loadFromDisk()
    }
}

class ProxyImage(private val filename: String) : Image {
    private var realImage: Image? = null

    override fun display() {
        println("ProxyImage: Displaying $filename")
        if (realImage == null)
            realImage = RealImage(filename)
        realImage!! .display()
    }
}

class ProxyTest {
    @Test
    fun testProxy(){
        val image = ProxyImage("test.jpg")

        //loading from disk
        image.display()
        println("-------------")

        //loading from cache
        image.display()
    }
}