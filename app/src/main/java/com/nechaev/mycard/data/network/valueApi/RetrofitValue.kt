package com.nechaev.mycard.data.network.valueApi

import com.google.gson.GsonBuilder
import com.nechaev.mycard.data.network.deserializers.ValueDeserializer
import com.nechaev.mycard.data.content.value.ValueRoot
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitValue {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private const val baseUrl = "https://www.cbr-xml-daily.ru/"

    private val retrofitValueBuider = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(buildGsonConverterFactory()).build()


    private fun buildGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(ValueRoot::class.java, ValueDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    fun getRetrofitValue(): Retrofit {
        return retrofitValueBuider
    }
}