package android.baseframework.core.repository.config

import android.baseframework.core.config.Constant
import android.text.TextUtils
import com.google.gson.Gson
import com.jayfeng.lesscode.core.CacheLess
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Type

class RxRetrofitCache {

    fun <T> load(cacheKey: String, expireTime: Long, fromNetwork: Observable<T>, forceRefresh: Boolean, type: Type): Observable<T> {
        var fromNetwork = fromNetwork

        val fromCache = Observable.create(ObservableOnSubscribe<T> { emitter ->
            val cache = CacheLess.`$get`(cacheKey, expireTime)

            if (!TextUtils.isEmpty(cache)) {
                val result = Gson().fromJson<T>(cache, type)
                emitter.onNext(result)
            } else {
                emitter.onComplete()
            }
        }).subscribeOn(Schedulers.io())

        fromNetwork = fromNetwork.subscribeOn(Schedulers.newThread())
                .map { mapResult ->
                    val cache = Gson().toJson(mapResult)
                    CacheLess.`$set`(cacheKey, cache)
                    mapResult
                }

        if (forceRefresh) {
            return fromNetwork
        } else {
            return Observable.concat(fromCache, fromNetwork)
        }
    }

    fun <T> loadCacheOnly(cacheKey: String, type: Type): T? {
        val cache = CacheLess.`$get`(cacheKey, Constant.TIME_ONE_HOUR * 10000)
        if (!TextUtils.isEmpty(cache)) {
            var result: T? = null
            try {
                result = Gson().fromJson<T>(cache, type)
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }

            return result
        }

        return null
    }

}