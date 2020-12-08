package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

class ComplexSystemStore(private val filePath: String){
    private val cache: HashMap<String, String>

    init {
        println("Reading data from file: $filePath")
        cache = HashMap()
        //reading properties from file and putting to cache
    }

    fun store(key: String, value: String){
        cache[key] = value
    }

    fun read(key: String) = cache[key] ?: ""
    fun commit() = println("Storing cached data to file $filePath")
}

data class User(val login: String)

//Facade
class UserRepository {
    private val systemPreferences = ComplexSystemStore("/data/default.prefs")

    fun save(user: User){
        systemPreferences.store("USER_KEY", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("USER_KEY"))
}

class FacadeTest {
    @Test
    fun testFacade(){
        val userRepo = UserRepository()
        val user = User("john")
        userRepo.save(user)

        val retrievedUser = userRepo.findFirst()

        Assert.assertEquals(retrievedUser.login, "john")
    }
}