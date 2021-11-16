package com.nechaev.mycard.model.valute.network

import com.google.gson.GsonBuilder
import com.nechaev.mycard.model.valute.entities.ValuteDeserializer
import com.nechaev.mycard.model.valute.entities.ValuteRoot
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitValue {

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build();

    private const val baseUrl = "https://www.cbr-xml-daily.ru/"

    private val retrofitValueBuider = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(buildGsonConverterFactory()).build()


    private fun buildGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(ValuteRoot::class.java, ValuteDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    fun getRetrofitValue(): Retrofit {
        return retrofitValueBuider
    }
}