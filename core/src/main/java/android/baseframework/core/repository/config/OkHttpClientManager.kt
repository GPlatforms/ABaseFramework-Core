package android.baseframework.core.repository.config

import android.baseframework.core.config.BCGlobal
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File


class OkHttpClientManager {

    companion object {
        fun getInstance() = OkHttpClientManager()
    }

    fun createOkHttpClient() : OkHttpClient? {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder()
                .hostnameVerifier { _, _ -> true }
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(getOkHttpCache())
                .retryOnConnectionFailure(true)
                .build()

        return okHttpClient
    }

    /**
     * global http header setting
     */
    private val headerInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        val newRequestBuilder = originalRequest.newBuilder()

        val timestamp = System.currentTimeMillis()
        newRequestBuilder.header("Content-Type", "application/json")

        chain.proceed(newRequestBuilder.build())
    }

    private fun getOkHttpCache(): Cache {
        val cacheFile = File(BCGlobal.context?.cacheDir, "OkCache")
        val cache = Cache(cacheFile, 1024 * 1024 * 50)
        return cache
    }
}