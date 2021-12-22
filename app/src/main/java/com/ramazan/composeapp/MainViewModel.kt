package com.ramazan.composeapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramazan.composeapp.data.cat.CatRepository
import com.ramazan.composeapp.data.cat.models.Resource
import com.ramazan.composeapp.data.text.TextRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val textRepository: TextRepository,
    private val catRepository: CatRepository
) : ViewModel() {

    private var textState by mutableStateOf("")
    private var catState by mutableStateOf("")
    private var loading by mutableStateOf(false)
    private var messageState by mutableStateOf("")

    val text: String
        get() = textState

    val catUrl: String
        get() = catState

    val isLoading: Boolean
        get() = loading

    val msg: String
        get() = messageState

    fun onChangeData() {
        viewModelScope.launch {
            catRepository.getCat().collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        catState = it.data?.url ?: ""
                        loading = false
                        textState = textRepository.getText()
                    }
                    Resource.Status.ERROR -> {
                        messageState = it.message ?: ""
                        loading = false
                    }
                    Resource.Status.LOADING -> {
                        loading = true
                    }
                }
            }

        }

    }
}