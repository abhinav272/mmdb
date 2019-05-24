package com.abhinav.mmdb.data.repo

import android.util.Log
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.parseError
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Result<T> {

        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
//                Log.e("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }


        return result

    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        try {
            val response = call.invoke()
            return if (response.isSuccessful) Result.Success(response.body()!!)
            else{
                val parseError = response.parseError()
                Result.Failure(parseError.statusCode, parseError.statusMessage)
            }
        } catch (e: Exception) {
            Log.e("Server Error - ", "exception caught ${e.printStackTrace()}")
            val eNew = when (e) {
                is UnknownHostException -> {
                    Exception("No Internet")
                }
                else -> Exception(errorMessage)
            }
            return Result.Error(eNew)
        }
    }
}