package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

interface Device {
    var volume: Int
    fun getName(): String
}

class Radio : Device {
    override var volume = 0
    override fun getName() = "Radio $this"
}

class TV : Device {
    override var volume = 0
    override fun getName() = "TV $this"
}

interface Remote {
    fun volumeUp()
    fun volumeDown()
}

class BasicRemote(val device: Device) : Remote {
    override fun volumeUp() {
        device.volume++
        println("${device.getName()} volume up: ${device.volume}")
    }

    override fun volumeDown() {
        device.volume--
        println("${device.getName()} volume up: ${device.volume}")
    }
}

class BridgeTest {
    @Test
    fun testBridge(){
        val tv = TV()
        val radio = Radio()

        val tvRemote = BasicRemote(tv)
        val radioRemote = BasicRemote(radio)

        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeUp()
        tvRemote.volumeDown()

        radioRemote.volumeUp()
        radioRemote.volumeDown()
        radioRemote.volumeUp()

        Assert.assertEquals(tvRemote.device.volume, 2)
        Assert.assertEquals(radioRemote.device.volume, 1)
    }
}