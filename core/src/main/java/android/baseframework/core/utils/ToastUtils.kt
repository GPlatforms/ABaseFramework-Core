package android.baseframework.core.utils

import android.app.Activity
import android.app.Dialog
import android.app.Fragment
import android.content.Context
import android.support.annotation.StringRes
import android.text.TextUtils
import android.widget.PopupWindow
import android.widget.Toast
import android.support.v4.app.Fragment as SupportFragment

/**
 * Created by Neil Zheng on 2017/6/19.
 */

private var mLastShowString: String? = null
private var mLastShowTimestamp: Long = 0
private val DEFAULT_TOAST_INTERVAL = 2000

fun Activity.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(this, message, duration)
}

fun Fragment.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(this.context, message, duration)
}

fun SupportFragment.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(this.context, message, duration)
}

fun Dialog.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(this.context, message, duration)
}

fun PopupWindow.showToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(this.contentView.context, message, duration)
}

fun Activity.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this.getString(resId), duration)
}

fun Fragment.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this.getString(resId), duration)
}

fun SupportFragment.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this.getString(resId), duration)
}

fun Dialog.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this.context.getString(resId), duration)
}

fun PopupWindow.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(this.contentView.context.getString(resId), duration)
}

private fun doShowToast(context: Context, message: String?, duration: Int) {
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

