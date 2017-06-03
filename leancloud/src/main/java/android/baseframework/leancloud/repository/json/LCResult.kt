package android.baseframework.leancloud.repository.json

import android.support.annotation.Keep

@Keep
data class LCResult<T>(val code: Int,
                       val error: String?,
                       val result: T?) {
}