package android.baseframework.core.example.extension

import android.baseframework.core.config.Api

fun Api.getNextNextUrl(): String {
    return SERVER_URL + "v1/aaa.json"
}