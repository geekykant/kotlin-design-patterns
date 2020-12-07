package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

sealed class Country {
    object Canada: Country()
}

object Spain : Country()
class Greece(val someProperty: String) : Country()
data class USA(val someProperty: String): Country()

class Currency (val code: String)

object CurrencyFactory {
    fun currencyForCountry(country: Country): Currency =
        when(country){
            is Spain -> Currency("EUR")
            is Greece -> Currency("EUR")
            is USA -> Currency("USD")
            is Country.Canada -> Currency("CAD")
        }
}

class FactoryMethodTest {
    @Test
    fun currencyTest(){
        val greekCurrency = CurrencyFactory.currencyForCountry(Greece("")).code
        println("Greek currency: $greekCurrency")

        val usaCurrency = CurrencyFactory.currencyForCountry(USA("")).code
        println("USA currency: $usaCurrency")

        Assert.assertEquals(greekCurrency, "EUR")
        Assert.assertEquals(usaCurrency, "USD")
    }
}