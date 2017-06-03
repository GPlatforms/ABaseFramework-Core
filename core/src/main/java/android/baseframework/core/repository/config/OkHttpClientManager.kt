package android.baseframework.core.repository.config

import android.baseframework.core.config.Global
import com.jayfeng.lesscode.core.EncodeLess
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File


class OkHttpClientManager {

    companion object {
        fun getInstance() = Holder.instance
    }

    private object Holder {
        val instance = OkHttpClientManager()
    }

    fun createOkHttpClient() : OkHttpClient? {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder()
                .hostnameVerifier { _, _ -> true }
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(getOkHttpCache())
                .build()

        return okHttpClient
    }

    /**
     * global http header setting
     */
    private val headerInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        val newRequestBuilder = originalRequest.newBuilder()

        newRequestBuilder.header("X-LC-Id", "TVrc4NCgBT7DtLnL1mrjlFwg-gzGzoHsz")
        val timestamp = System.currentTimeMillis()
        newRequestBuilder.header("X-LC-Sign", EncodeLess.`$md5`(timestamp.toString() + "ofWyXDaU2Lzca6ntCpvvrCsj") + "," + timestamp + ",master")
        newRequestBuilder.header("Content-Type", "application/json")

        chain.proceed(newRequestBuilder.build())
    }

    private fun getOkHttpCache(): Cache {
        val cacheFile = File(Global.context?.cacheDir, "OkCache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50)
        return cache
    }
}