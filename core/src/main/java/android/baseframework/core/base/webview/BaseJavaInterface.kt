package android.baseframework.core.base.webview

import android.Manifest
import android.app.Activity
import android.baseframework.core.utils.startActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by Neil Zheng on 2017/6/20.
 */

class BaseJavaInterface(val context: Context) {

    @JavascriptInterface
    fun dial(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone))
        context.startActivity(intent)
    }

}
