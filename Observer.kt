package com.paavam.learningcurve

import android.util.Log
import org.junit.Test
import java.io.File

interface EventListener {
    fun update(eventType: String?, file: File?)
}

class EventManger(vararg operations: String) {
    var listeners = hashMapOf<String, ArrayList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList()
        }
    }

    fun subscribe(eventType: String?, listener: EventListener) {
        val users = listeners.get(eventType)
        users?.add(listener)
    }

    fun unsubscribe(eventType: String?, listener: EventListener) {
        val users = listeners.get(eventType)
        users?.remove(listener)
    }

    fun notify(eventType: String?, file: File?) {
        val users = listeners.get(eventType)
        users?.let {
            for (listener in it) {
                listener.update(eventType, file)
            }
        }
    }
}

class Editor {
    var events: EventManger = EventManger("open", "save")
    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file)
    }

    fun saveFile() {
        file?.let {
            events.notify("save", file)
        }
    }
}

class EmailNotificationListener(private val email: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Email to $email: Someone has performed operation $eventType on file ${file?.name}")
    }
}

class LogOpenListener(var filename: String) : EventListener {
    override fun update(eventType: String?, file: File?) {
        println("Save to log $filename: Someone has performed operation $eventType on file ${file?.name}")
    }
}

class ObserverTest {
    @Test
    fun testObserver() {
        val logListener = LogOpenListener("path/to/log/file.txt")
        val emailListener = EmailNotificationListener("test@test.com")

        val editor = Editor()
        editor.events.subscribe("open", logListener)
        editor.events.subscribe("open", emailListener)
        editor.events.subscribe("save", emailListener)

        editor.openFile("test.txt")
        editor.saveFile()
    }
}