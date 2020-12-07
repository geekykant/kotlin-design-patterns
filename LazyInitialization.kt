package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

class AlertBox {
    var message: String? = null

    fun show() {
        println("AlertBox $this: $message")
    }
}

class Window {
    val box by lazy { AlertBox() }

    fun showMessage(message: String) {
        box.message = message
        box.show()
    }
}

class Window2 {
    lateinit var box: AlertBox

    fun showMessage(message: String) {
        box = AlertBox()
        box.message = message
        box.show()
    }
}

class WindowTest {
    @Test
    fun windowsTest() {
        val window = Window()
        window.showMessage("Hello window")
        Assert.assertNotSame(window.box, null)

        val window2 = Window2()
        //println(window2.box) //error
        window2.showMessage("Window 2")
        Assert.assertNotSame(window2.box, null)
    }
}