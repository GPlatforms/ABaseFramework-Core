package android.baseframework.core.example

import android.baseframework.core.BCApp
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter


class MyApp : BCApp() {

    override fun onCreate() {
        super.onCreate()

        initGlobalHeaderAndFooterView()
    }


    fun initGlobalHeaderAndFooterView() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout ->
            layout.setPrimaryColors(R.color.colorPrimary, android.R.color.white)
            layout.setEnableHeaderTranslationContent(false)
            MaterialHeader(context).setShowBezierWave(false)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreater { context, layout ->
            ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale)
        }
    }
}
