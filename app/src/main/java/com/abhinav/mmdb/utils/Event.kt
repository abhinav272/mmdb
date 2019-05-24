package com.abhinav.mmdb.utils

class Event<T>(private val content: T) {
    var isAlreadyHandled = false
        private set

    fun getContent(): T? {
        if (!isAlreadyHandled) {
            isAlreadyHandled = true
            return content
        }
        return null
    }

    fun peekContent(): T {
        return content
    }
}