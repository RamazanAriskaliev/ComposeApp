package com.ramazan.composeapp.data.cat

import com.ramazan.composeapp.data.cat.models.Cat
import com.ramazan.composeapp.data.cat.models.Resource
import kotlinx.coroutines.flow.Flow

interface CatRepository {

    suspend fun getCat(): Flow<Resource<Cat>>
}