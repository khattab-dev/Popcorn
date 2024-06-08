package com.slayer.network


inline fun <T : Any> safeApiCall(apiCall: () -> T): Resource<T> {
    return try {
        Resource.Success(data = apiCall())
    } catch (e: Exception) {
        Resource.Failure(exception = e)
    }
}