package com.pedroluis.fastshoptest.features.utils

import kotlinx.coroutines.runBlocking

inline fun test(block: Test.() -> Unit) { Test().apply { block() } }

class Test {

    fun act(block: suspend () -> Unit) {
        apply { runBlocking {
            block.invoke()
        } }
    }

    fun arrange(block: suspend () -> Unit) {
        apply {
            runBlocking {
                block.invoke()
            }
        }
    }

    fun assert(block: suspend () -> Unit) {
        apply {
            runBlocking {
                block.invoke()
            }
        }
    }
}