package android.baseframework.core.utils

import android.app.Activity
import android.app.Dialog
import android.app.Fragment
import android.baseframework.core.BaseCoreApp
import android.baseframework.core.config.Global
import android.content.Context
import android.support.annotation.StringRes
import android.text.TextUtils
import android.widget.PopupWindow
import android.widget.Toast
import android.support.v4.app.Fragment as SupportFragment

/**
 * Created by Neil Zheng on 2017/6/19.
 */

@Volatile private var mLastShowString: String? = null
@Volatile private var mLastShowTimestamp = 0L
private val DEFAULT_TOAST_INTERVAL = 2000L

fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(Global.context!!, message, duration)
}

fun showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(Global.context!!.getString(resId), duration)
}

private fun doShowToast(context: Context, message: String, duration: Int) {
    if (!isToastShowing(message)) {
        Toast.makeText(context, message, duration).show()
        mLastShowTimestamp = System.currentTimeMillis()
        mLastShowString = message
    }
}

private fun isToastShowing(string: String?): Boolean {
    return TextUtils.equals(string, mLastShowString)
            && System.currentTimeMillis() - mLastShowTimestamp <= DEFAULT_TOAST_INTERVAL
}

