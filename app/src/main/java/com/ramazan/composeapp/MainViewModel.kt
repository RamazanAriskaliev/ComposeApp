package com.ramazan.composeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel(val repository: TextRepository) : ViewModel() {

    private var textState by mutableStateOf("")

    val text: String
        get() = textState

    fun changeText() {
        textState = repository.getText()
    }
}