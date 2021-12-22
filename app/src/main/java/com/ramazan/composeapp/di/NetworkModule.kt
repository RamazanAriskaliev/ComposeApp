package com.ramazan.composeapp

import android.util.Log
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
import org.koin.dsl.module

val networkModule = module {

    single<TextRepository> {
        TextRepositoryImpl(get())
    }

    single {
        arrayListOf(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
        )
    }

    single<CatRepository> {
        CatRepositoryImpl(get())
    }
    single {
        HttpClient(Android) {

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

                engine {
                    connectTimeout = Constants.TIME_OUT
                    socketTimeout = Constants.TIME_OUT
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single {
        CatService(get())
    }
}