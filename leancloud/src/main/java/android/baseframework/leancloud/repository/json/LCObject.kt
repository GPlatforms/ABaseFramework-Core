package android.baseframework.leancloud.repository.json

import android.support.annotation.Keep

@Keep
data class LCObject<T>(var code:Int,
                  var error: String?,
                  var objectId: String?,
                  var results: List<T>?) {
}