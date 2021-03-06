package android.baseframework.core.utils

import android.content.Context
import android.util.TypedValue



/**
 * Created by Neil Zheng on 2017/7/4.
 */

fun dp2pxf(context: Context, dpVal: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.resources.displayMetrics)
}

fun dp2px(context: Context, dpVal: Float): Int {
    return dp2pxf(context, dpVal).toInt()
}

fun sp2pxf(context: Context, spVal: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.resources.displayMetrics)
}

fun sp2px(context: Context, spVal: Float): Int {
    return sp2pxf(context, spVal).toInt()
}

fun px2dpf(context: Context, pxVal: Float): Float {
    return pxVal / context.resources.displayMetrics.density
}

fun px2dp(context: Context, pxVal: Float): Int {
    return px2dpf(context, pxVal).toInt()
}

fun px2spf(context: Context, pxVal: Float): Float {
    return pxVal / context.resources.displayMetrics.scaledDensity
}

fun px2sp(context: Context, pxVal: Float): Int {
    return px2spf(context, pxVal).toInt()
}