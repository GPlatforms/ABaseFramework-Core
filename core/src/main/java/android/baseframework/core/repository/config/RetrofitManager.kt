package android.baseframework.core.repository.config

import android.baseframework.core.config.Api
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitManager {

    private fun buildBaseRetrofit(): Retrofit.Builder {
        val retrofitBuilder = Retrofit.Builder()
                .client(OkHttpClientManager.getInstance().createOkHttpClient())
        retrofitBuilder.baseUrl(Api.SERVER_URL)
        return retrofitBuilder
    }

    fun getRetrofit(): Retrofit {
        return buildBaseRetrofit()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getRetrofitWithFormatDate(): Retrofit {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
        return buildBaseRetrofit()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun getScalarsRetrofit(): Retrofit {
        return buildBaseRetrofit()
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
    }
}