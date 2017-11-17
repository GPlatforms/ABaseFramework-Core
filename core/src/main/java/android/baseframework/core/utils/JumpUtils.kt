package android.baseframework.core.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

fun <T: Activity> Context.startActivity(clazz: Class<T>) {
    startActivity(Intent(this, clazz))
}
