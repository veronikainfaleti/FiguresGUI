package com.gui.controller

import java.io.*

class FileWriter {
    @Throws(FileSystemException::class)
    fun write(obj: Any, filename: String) {
        try {
            FileOutputStream(filename).use { fileOutput ->
                val objectOutput = ObjectOutputStream(fileOutput)
                objectOutput.writeObject(obj)
            }
        } catch (e: IOException) {
            throw FileSystemException(
                    file = File(filename),
                    reason = e.message
            )
        }
    }

    @Throws(FileSystemException::class, NoSuchElementException::class)
    fun read(filename: String): Any {
        try {
            FileInputStream(filename).use { fileInput ->
                val objectInput = ObjectInputStream(fileInput)
                return objectInput.readObject()
            }
        } catch (e: IOException) {
            throw FileSystemException(
                    file = File(filename),
                    reason = e.message
            )
        } catch (e: ClassNotFoundException) {
            throw NoSuchElementException("Class not found. ${e.message}")
        }
    }
}