package com.paavam.learningcurve

import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test

interface DataSource

class DatabaseDataSource : DataSource
class NetworkDataSource : DataSource

abstract class DataSourceFactory {
    abstract fun makeDataSource(): DataSource

    companion object {
        inline fun <reified T : DataSource> createFactory(): DataSourceFactory =
            when (T::class) {
                DatabaseDataSource::class -> DatabaseFactory()
                NetworkDataSource::class -> NetworkFactory()
                else -> throw IllegalArgumentException()
            }
    }
}

class NetworkFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = NetworkDataSource()
}

class DatabaseFactory : DataSourceFactory() {
    override fun makeDataSource(): DataSource = DatabaseDataSource()
}

class AbstractFactoryTest {
    @Test
    fun aftest(){
        val dataSourceFactory = DataSourceFactory.createFactory<DatabaseDataSource>()
        val dataSource = dataSourceFactory.makeDataSource()

        println("Created datasource $dataSource")
        assertThat(dataSource, instanceOf(DatabaseDataSource::class.java))
    }
}