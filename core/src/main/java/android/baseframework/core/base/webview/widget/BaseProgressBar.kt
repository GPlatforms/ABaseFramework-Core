package android.baseframework.core.base.webview.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar

/**
 * Created by Neil Zheng on 2017/7/3.
 */

class BaseProgressBar : android.widget.ProgressBar {

    constructor(context: android.content.Context) : super(context)
    constructor(context: android.content.Context, attrs: android.util.AttributeSet) : super(context, attrs)
    constructor(context: android.content.Context, attrs: android.util.AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}
