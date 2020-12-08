package com.paavam.learningcurve

import org.junit.Test

interface Command {
    fun execute()
}

class OrderAddCommand(var id: Long) : Command {
    override fun execute() {
        println("Adding order with id: $id")
    }
}

class OrderPayCommand(var id: Long) : Command {
    override fun execute() {
        println("Paying order with id: $id")
    }
}

class CommandProcessor {
    private val queue = arrayListOf<Command>()

    fun addToQueue(command: Command) = apply { queue.add(command) }

    fun processCommands(): CommandProcessor = apply {
        queue.forEach { it.execute() }
        queue.clear()
    }
}

class CommandTest {
    @Test
    fun testCommand(){
        CommandProcessor()
            .addToQueue(OrderAddCommand(0L))
            .addToQueue(OrderPayCommand(12L))
            .addToQueue(OrderAddCommand(15L))
            .processCommands()
    }
}