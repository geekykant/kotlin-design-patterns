package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

open class Equipment(
    open val price: Int,
    val name: String
)

open class Composite(name: String) : Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.map { it.price }.sum()

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class Computer : Composite("PC")
class Processor : Equipment(10, "Processor")
class HardDrive : Equipment(30, "Hard Drive")
class Memory : Composite("Memory")
class RAM : Equipment(90, "RAM")
class ROM : Equipment(50, "ROM")

class CompositeTest {
    @Test
    fun testComposite() {
        val memory = Memory()
            .add(RAM())
            .add(ROM())
        val pc = Computer()
            .add(memory)
            .add(Processor())
            .add(HardDrive())

        println("PC price: ${pc.price}")

        Assert.assertEquals(pc.name, "PC")
        Assert.assertEquals(pc.price, 180)
    }
}