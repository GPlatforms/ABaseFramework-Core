package android.baseframework.core

import android.app.Application
import android.baseframework.core.config.BCGlobal


open class BCApp: Application() {

    override fun onCreate() {
        super.onCreate()

        BCGlobal.context = this
    }
}