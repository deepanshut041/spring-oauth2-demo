package io.github.deepanshut041.sod.util

import java.util.*

fun <T : Any> Optional<T>.toNullable(): T? = this.orElse(null)