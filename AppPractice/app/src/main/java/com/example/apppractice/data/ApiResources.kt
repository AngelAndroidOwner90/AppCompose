package com.example.apppractice.data

// Response api
sealed class ApiResources<T>(
    val data: T? = null,
    val message: String? = null,
    val loading: Boolean? = null) {

    class Success<T>(data: T?) : ApiResources<T>(data)
    class Error<T>(message: String?, data: T? = null) : ApiResources<T>(data, message)
    class Loading<T>(loading: T? = null) : ApiResources<T>(loading)
}