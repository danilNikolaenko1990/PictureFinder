package com.daniln.picturefinder.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> callWithWrappedResult(
    dispatcher: CoroutineDispatcher,
    function: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(function.invoke())
        } catch (throwable: Throwable) {
            ResultWrapper.Error(throwable.message.toString())
        }
    }
}

class DataFetchingProblemException constructor(e: Throwable) : Exception(e)