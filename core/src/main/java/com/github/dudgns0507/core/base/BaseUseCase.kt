package com.github.dudgns0507.core.base

import com.github.dudgns0507.core.R
import com.github.dudgns0507.core.util.network.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<T : Any, E : Any> {
    protected abstract suspend fun execute(parameter: T) : ResultWrapper<T, E>

    suspend operator fun invoke(parameter: T) : ResultWrapper<T, E> {
        return try {
            withContext(Dispatchers.IO) {
                execute(parameter)
            }
        } catch (e: Throwable) {
            ResultWrapper.UnknownError(e)
        }
    }
}