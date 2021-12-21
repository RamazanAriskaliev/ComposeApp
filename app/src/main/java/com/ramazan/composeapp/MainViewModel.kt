package com.ramazan.composeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel(private val repository: TextRepository) : ViewModel() {

    private var textState by mutableStateOf(repository.getText())

    val text: String
        get() = textState

    fun onTextChange() {
        textState = repository.getText()
    }
}