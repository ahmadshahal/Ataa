package com.hero.ataa.shared

sealed class DataState<T> {
    class Success<T>(data: T) : DataState<T>()
    class SuccessWithoutData<T>() : DataState<T>()
    class Error<T>(message: String) : DataState<T>()
    class Loading<T> : DataState<T>()
}
