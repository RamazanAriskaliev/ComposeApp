package com.ramazan.composeapp.data.cat

import com.ramazan.composeapp.data.cat.models.Cat
import com.ramazan.composeapp.data.cat.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class CatRepositoryImpl(private val catService: CatService) : CatRepository {
    val cache: Queue<Cat> = LinkedList<Cat>()

    override suspend fun getCat(): Flow<Resource<Cat>> {
        return flow<Resource<Cat>> {
            if (cache.isEmpty()) {
                emit(Resource.loading())
                try {
                    val cats = catService.getCatFromApi()
                    cache.addAll(cats)
                    emit(Resource.success(cache.poll()))
                } catch (e: Exception) {
                    emit(Resource.error(e.message))
                }
            } else {
                emit(Resource.success(cache.poll()))
            }

        }


    }
}