package com.codetron.foodmarketmvp.network

import com.codetron.foodmarketmvp.BuildConfig.BASE_URL
import com.google.gson.Gson
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    private val apiEndPoint = "api/"
    private val datePatternJson = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private val datePattern = "dd-MM-yyyy"
    private val timeZoneId = "UTC"

    private val gson by lazy {
        Gson().newBuilder()
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

    private val client: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL + apiEndPoint)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    fun getApi(token: String? = null): FoodMarketApi {
        val okHttpClient = client.addInterceptor(HeaderInterceptor(token)).build()

        return retrofit
            .client(okHttpClient)
            .build()
            .create(FoodMarketApi::class.java)
    }

}