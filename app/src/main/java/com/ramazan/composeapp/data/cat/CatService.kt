package com.ramazan.composeapp.data.cat

import com.ramazan.composeapp.Constants
import com.ramazan.composeapp.data.cat.models.Cat
import io.ktor.client.*
import io.ktor.client.request.*

class CatService(val client: HttpClient) {
    suspend fun getCatFromApi(): List<Cat> {
        return client.get<List<Cat>>("${Constants.BASE_URL}?limit=5")
    }
}