package com.paavam.learningcurve

import org.junit.Test

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() {
        println("Normal coffee machine making small coffee")
    }

    override fun makeLargeCoffee() {
        println("Normal coffee machine making large coffee")
    }
}

//Decorator

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine): CoffeeMachine by coffeeMachine {
    //Overriding behaviour
    override fun makeLargeCoffee() {
        println("Enhanced coffee machine: Making large coffee")
    }

    //Extending behaviour
    fun makeMilkCoffee(){
        println("Enhanced coffee machine: Making milk coffee")
        makeSmallCoffee()
        println("Enhanced coffee machine: Adding milk")
    }
}

class DecoratorTest {
    @Test
    fun testDecorator(){
        val normalMachine = NormalCoffeeMachine()
        val enhancedCoffeeMachine = EnhancedCoffeeMachine(normalMachine)

        enhancedCoffeeMachine.makeSmallCoffee()
        println("------------")
        enhancedCoffeeMachine.makeLargeCoffee()
        println("------------")
        enhancedCoffeeMachine.makeMilkCoffee()
    }
}