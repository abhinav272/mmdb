package com.abhinav.mmdb.data.model

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Failure(val code: Int?, val message: String) : Result<Nothing>()
}