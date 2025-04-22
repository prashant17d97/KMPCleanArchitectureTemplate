package com.prashant.core.utils

object LogUtil {
    enum class LogType { DEBUG, INFO, WARN, ERROR, VERBOSE }

    fun logcat(
        keyName: String,
        value: Any?,
        type: LogType = LogType.DEBUG,
    ) {
//        if (!BuildConfig.DEBUG) return // Only log in DEBUG mode
//        val stackTrace = Throwable().stackTrace[1] // Caller information
//        val tag = stackTrace.className.substringAfterLast(".") // Auto Class Name
//        val fileName = stackTrace.fileName // File Name for clickable link
//        val methodName = stackTrace.methodName // The name of the method calling the log function.
//        Log.d("LogUtil", "logcat: $methodName")
//        val lineNumber = stackTrace.lineNumber // Auto Line Number
//
//        val dataType = value?.let { it::class.simpleName } ?: "null"
//
//        val logMessage = "($fileName:$lineNumber) ➔ $keyName ($dataType) = $value"
//
//        when (type) {
//            LogType.DEBUG -> Log.d(tag, logMessage)
//            LogType.INFO -> Log.i(tag, logMessage)
//            LogType.WARN -> Log.w(tag, logMessage)
//            LogType.ERROR -> Log.e(tag, logMessage)
//            LogType.VERBOSE -> Log.v(tag, logMessage)
//        }
    }
}
