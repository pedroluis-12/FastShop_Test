package com.pedroluis.fastshoptest.utils

import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private val Activity.argumentsFinder: Activity.(String) -> Any?
    get() ={ intent.extras?.get(it) }

internal fun <T: Any> Activity.bindBundle(key: String, default: T? = null):
        ReadOnlyProperty<Activity, T> = if (default == null) {
    required(key, argumentsFinder)
} else {
    requiredDefault(key, default, argumentsFinder)
}

@Suppress("UNCHECKED_CAST")
private fun <T, B : Any?> required(key: String, finder: T.(String) -> Any?) =
    Lazy { t: T, desc ->
        t.finder(key) as B ?: bundleNotFound(
            key,
            desc
        )
    }

@Suppress("UNCHECKED_CAST")
private fun <T, B: Any?> requiredDefault(key: String, default: B, finder: T.(String) -> Any?) =
    Lazy {
        t: T, _ ->
        t.finder(key) as B? ?: default
    }

private fun bundleNotFound(key: String, desc: KProperty<*>): Nothing =
    throw IllegalStateException("Bundle KEY $key for '${desc.name}' not found.")