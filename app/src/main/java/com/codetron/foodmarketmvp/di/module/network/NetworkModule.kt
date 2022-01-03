package com.codetron.foodmarketmvp.di.module.network

import com.codetron.foodmarketmvp.BuildConfig
import com.codetron.foodmarketmvp.network.FoodMarketApi
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
class NetworkModule {

    private val apiEndPoint = "api/"
    private val datePatternJson = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private val datePattern = "dd-MM-yyyy"
    private val timeZoneId = "UTC"

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson().newBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat(datePatternJson)
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ ->
                SimpleDateFormat(datePatternJson, Locale.ENGLISH).apply {
                    timeZone = TimeZone.getTimeZone(timeZoneId)
                }.parse(json.asString)
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _ ->
                JsonPrimitive(SimpleDateFormat(datePattern, Locale.ENGLISH).apply {
                    timeZone = TimeZone.getTimeZone(timeZoneId)
                }.format(src))
            })
            .create()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("accept", "application/json")
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL + apiEndPoint)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FoodMarketApi {
        return retrofit.create(FoodMarketApi::class.java)
    }

}