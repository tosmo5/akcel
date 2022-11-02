package com.tosmo.akcel.listener

fun interface ReadListener<T : Any> {
    fun invoke(data: T)
}