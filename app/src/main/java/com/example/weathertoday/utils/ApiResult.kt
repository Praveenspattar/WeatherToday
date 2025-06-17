package com.example.weathertoday.utils

sealed class ApiResult<out T> {
    data object Idle: ApiResult<Nothing>()
    data object Loading: ApiResult<Nothing>()
    data class Success<T>(val data:T): ApiResult<T>()
    data class Error(val message: String, val throwable: Throwable? = null): ApiResult<Nothing>()
}