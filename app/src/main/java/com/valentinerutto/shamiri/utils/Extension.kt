package com.valentinerutto.shamiri.utils

fun String?.orUnknown(unknown: String): String {
    return if (this.isNullOrEmpty()) unknown else this
}

fun <T> T?.orUnknown(defaultValue: T): T {
    return this ?: defaultValue
}