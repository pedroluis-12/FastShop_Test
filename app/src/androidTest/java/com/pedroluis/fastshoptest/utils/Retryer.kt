package com.pedroluis.fastshoptest.utils

fun retryer(times: Int = 3, timeout: Long = 300, function:() -> Unit) {
    for (i in 0 until times) {
        try {
            function()
            return
        } catch (e: Throwable) {
            if (i == times) {
                throw e
            }
            Thread.sleep(timeout)
        }
    }
}