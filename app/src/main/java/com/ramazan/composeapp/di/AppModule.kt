package com.ramazan.composeapp.di

import android.util.Log
import com.ramazan.composeapp.Constants
import com.ramazan.composeapp.MainViewModel
import com.ramazan.composeapp.data.cat.CatRepository
import com.ramazan.composeapp.data.cat.CatRepositoryImpl
import com.ramazan.composeapp.data.cat.CatService
import com.ramazan.composeapp.data.text.TextRepository
import com.ramazan.composeapp.data.text.TextRepositoryImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module {


    viewModel {
        MainViewModel(get(), get())
    }
}
