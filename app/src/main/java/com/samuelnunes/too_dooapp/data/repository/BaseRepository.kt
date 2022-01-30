package com.samuelnunes.too_dooapp.data.repository

import com.samuelnunes.too_dooapp.common.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

interface BaseRepository {

    fun <T> tryRequest(funcRemoteData: suspend () -> T) = flow {
        try {
            emit(Resource.Loading())
            kotlinx.coroutines.delay(5000)
            val data = funcRemoteData()
            emit(Resource.Success(data))
        } catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "Um erro inesperrado ocorreu!"))
        } catch (e: IOException){
            emit(Resource.Error("Verifique sua conexão com a internet!"))
        }
    }

}