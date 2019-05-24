package com.abhinav.mmdb.data.repo

import android.util.Log
import com.abhinav.mmdb.data.model.Result
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
                Log.e("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }


        return result

    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) return Result.Success(response.body()!!)
        } catch (e: Exception) {
            Log.e("testing ", "exception caught ${e.printStackTrace()}")
            val eNew = when (e) {
                is UnknownHostException -> {
                    Exception("No Internet")
                }
                else -> Exception("Something went wrong")
            }
            return Result.Error(eNew)
        }

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}