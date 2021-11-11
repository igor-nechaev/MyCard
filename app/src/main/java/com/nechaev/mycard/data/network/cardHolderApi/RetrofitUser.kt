package com.nechaev.mycard.data.network.cardHolderApi

import com.google.gson.GsonBuilder
import com.nechaev.mycard.data.network.deserializers.CardHolderListDeserializer
import com.nechaev.mycard.data.content.cardHolder.CardHolderList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUser {

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private const val baseUrl = "https://hr.peterpartner.net/test/android/v1/"

    private val retrofitUsersBuider = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(buildGsonConverterFactory()).build()

    private fun buildGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(CardHolderList::class.java, CardHolderListDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    fun getRetrofitUsers(): Retrofit {
        return retrofitUsersBuider
    }
}