package android.baseframework.core.base.webview.widget

import android.app.Activity
import android.baseframework.core.R
import android.baseframework.core.utils.dp2pxf
import android.content.Context
import android.support.annotation.ColorInt
import android.support.v7.widget.Toolbar
import android.util.AttributeSet

/**
 * Created by Neil Zheng on 2017/7/3.
 */

open class DefaultTitleBar : Toolbar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        elevation = dp2pxf(context, 2f)
        setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
        setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material)
        setNavigationOnClickListener { v ->
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }
}
