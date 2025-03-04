package com.infosys.data.remote

/**
 * A sealed class is a class that cannot be inherited by other classes.
 * This concept is used to restrict the inheritance of a class,
 * ensuring that no other class can derive from it.
 * OR
 * The primary purpose of a sealed class is to prevent further extension and modification,
 * which can be useful in scenarios where you want to provide a fixed implementation that should
 * not be altered.
 * */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Loading<T> : Resource<T>()
    class Error<T>(message: String) : Resource<T>(message = message)
}