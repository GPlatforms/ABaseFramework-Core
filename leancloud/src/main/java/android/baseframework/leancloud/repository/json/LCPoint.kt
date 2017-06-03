package android.baseframework.leancloud.repository.json

import android.support.annotation.Keep

@Keep
data class LCPoint(val __type: String = "Pointer",
                   val className: String?,
                   val objectId: String?) {
}