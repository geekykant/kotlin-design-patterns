package com.paavam.learningcurve

import org.junit.Assert
import org.junit.Test

//part 1
data class DisplayDataType(val index: Float, val data: String)

class DataDisplay {
    fun displayData(data: DisplayDataType) {
        println("Data is displayed: ${data.index} - ${data.data}")
    }
}


//part 2
data class DatabaseData(val position: Int, val amount: Int)

class DatabaseDataGenerator {
    fun generateData(): List<DatabaseData> {
        val list = arrayListOf<DatabaseData>()
        list.add(DatabaseData(2, 2))
        list.add(DatabaseData(3, 3))
        list.add(DatabaseData(6, 5))
        return list
    }
}

interface DatabaseDataConverter {
    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
}

class DataDisplayAdapter(val display: DataDisplay) : DatabaseDataConverter {
    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
        val returnList = arrayListOf<DisplayDataType>()
        for (datum in data) {
            val ddt = DisplayDataType(datum.position.toFloat(), datum.amount.toString())
            display.displayData(ddt)
            returnList.add(ddt)
        }
        return returnList
    }
}

class AdapterTest {
    @Test
    fun adapterTest(){
        val generator = DatabaseDataGenerator()
        val generatedData = generator.generateData()
        val adapter = DataDisplayAdapter(DataDisplay())
        val convertedData = adapter.convertData(generatedData)

        Assert.assertEquals(convertedData.size, 3)
    }
}