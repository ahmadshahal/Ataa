package com.hero.ataa.shared

sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()
    class SuccessWithoutData<T> : DataState<T>()
    data class Error<T>(val message: String) : DataState<T>()
    class Loading<T> : DataState<T>()
}