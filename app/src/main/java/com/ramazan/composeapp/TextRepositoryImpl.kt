package com.ramazan.composeapp

import java.util.*

class TextRepositoryImpl(private val items: ArrayList<String>) : TextRepository {

    private var lastProvided: String = ""

    override fun getText(): String {
        val random = Random()

        var newItem = lastProvided
        while (lastProvided == newItem) {
            val randomIndex = random.nextInt(items.size - 1)
            newItem = items[randomIndex]
        }
        lastProvided = newItem
        return newItem
    }
}