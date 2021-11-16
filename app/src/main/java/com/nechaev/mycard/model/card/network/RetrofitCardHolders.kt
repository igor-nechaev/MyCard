package com.nechaev.mycard.model.card.network

import com.google.gson.GsonBuilder
import com.nechaev.mycard.model.card.entities.CardHolderListDeserializer
import com.nechaev.mycard.model.card.entities.CardHolderList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitCardHolders {

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build();

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