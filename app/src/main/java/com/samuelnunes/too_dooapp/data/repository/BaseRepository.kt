package com.samuelnunes.too_dooapp.data.repository

import com.samuelnunes.too_dooapp.common.Resource
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.lang.Exception

open class BaseRepository {

    fun <ResultType> tryRequest(
        getRemoteData: suspend () -> ResultType
    ): Flow<Resource<ResultType>> = flow {
        try {
            emit(Resource.Loading())
            val data = getRemoteData()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Um erro inesperado ocorreu!"))
        } catch (e: IOException) {
            emit(Resource.Error("Verifique sua conexão com a internet!"))
        } catch (e: Exception) {
            emit(Resource.Error("Hmmmm, erro não esperado, mas já já vamos lidar com isso!"))
            Timber.e(e)
        }
    }

    inline fun <ResultType, RequestType> tryRequest(
        crossinline query: () -> Flow<ResultType>,
        crossinline fetch: suspend () -> RequestType,
        crossinline saveFetchResult: suspend (RequestType) -> Unit,
        crossinline onFetchFailed: (Throwable) -> Unit = { }
    ): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())

        val inCache = query().firstOrNull()
        whenCache(inCache) { resultType ->
            this.emit(Resource.Success(resultType))
        }

        val flow = try {
            val resultFetch = fetch()
            saveFetchResult(resultFetch)
            kotlinx.coroutines.delay(5000)
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            if (inCache == null || (inCache is List<*> && inCache.isEmpty())) {
                onFetchFailed(throwable)
                query().map { Resource.Error<ResultType>(throwable) }
            }else{
                query().map { Resource.Success(it) }
            }
        }

        emitAll(flow)
    }

    suspend fun <ResultType> whenCache(
        inCache: ResultType?,
        wrapper: suspend (ResultType) -> Unit,
    ) {
        if (inCache != null) {
            if (inCache is List<*>) {
                if (inCache.isNotEmpty()) {
                    wrapper(inCache)
                }
            } else {
                wrapper(inCache)
            }
        }
    }
}


