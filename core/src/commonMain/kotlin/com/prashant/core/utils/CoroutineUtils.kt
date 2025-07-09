package com.prashant.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

object CoroutineUtils {
    suspend inline fun <T> backgroundScope(noinline block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.IO, block = block)

    suspend inline fun <T> mainScope(noinline block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.Main, block = block)

    suspend inline fun <T> defaultScope(noinline block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.Default, block = block)
}
