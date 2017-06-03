package android.baseframework.leancloud.repository.json

import android.support.annotation.Keep

@Keep
class LCException {

    companion object {

        val ACCOUNT_ALREADY_LINKED = 208
        val CACHE_MISS = 120
        val CONNECTION_FAILED = 100
        val DUPLICATE_VALUE = 137
        val EMAIL_MISSING = 204
        val EMAIL_NOT_FOUND = 205
        val EMAIL_TAKEN = 203
        val EXCEEDED_QUOTA = 140
        val FILE_DELETE_ERROR = 153
        val FILE_DOWNLOAD_INCONSISTENT_FAILURE = 253
        val INCORRECT_TYPE = 111
        val INTERNAL_SERVER_ERROR = 1
        val INVALID_ACL = 123
        val INVALID_CHANNEL_NAME = 112
        val INVALID_CLASS_NAME = 103
        val INVALID_EMAIL_ADDRESS = 125
        val INVALID_FILE_NAME = 122
        val INVALID_FILE_URL = 126
        val INVALID_JSON = 107
        val INVALID_KEY_NAME = 105
        val INVALID_LINKED_SESSION = 251
        val INVALID_NESTED_KEY = 121
        val INVALID_PHONE_NUMBER = 127
        val INVALID_POINTER = 106
        val INVALID_QUERY = 102
        val INVALID_ROLE_NAME = 139
        val LINKED_ID_MISSING = 250
        val MISSING_OBJECT_ID = 104
        val MUST_CREATE_USER_THROUGH_SIGNUP = 207
        val NOT_INITIALIZED = 109
        val OBJECT_NOT_FOUND = 101
        val OBJECT_TOO_LARGE = 116
        val OPERATION_FORBIDDEN = 119
        val OTHER_CAUSE = -1
        val PASSWORD_MISSING = 201
        val PUSH_MISCONFIGURED = 115
        val RATE_LIMITED = 503
        val SCRIPT_ERROR = 141
        val SESSION_MISSING = 206
        val TIMEOUT = 124
        val UNKNOWN = 999
        val UNSUPPORTED_SERVICE = 252
        val USER_DOESNOT_EXIST = 211
        val USER_ID_MISMATCH = 209
        val USER_MOBILE_PHONENUMBER_TAKEN = 214
        val USER_MOBILEPHONE_MISSING = 212
        val USER_MOBILEPHONE_NOT_VERIFIED = 215
        val USER_WITH_MOBILEPHONE_NOT_FOUND = 213
        val USERNAME_MISSING = 200
        val USERNAME_PASSWORD_MISMATCH = 210
        val USERNAME_TAKEN = 202
        val VALIDATION_ERROR = 142
    }
}