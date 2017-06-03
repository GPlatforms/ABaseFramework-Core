package android.baseframework.leancloud.repository.json

import android.support.annotation.Keep

@Keep
data class LCUser(var sessionToken: String?,
                  var mobilePhoneNumber: String?,
                  var username: String?,
                  var nickname: String?) {
}