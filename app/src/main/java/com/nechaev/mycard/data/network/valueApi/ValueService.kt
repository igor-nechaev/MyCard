package com.nechaev.mycard.data.network.valueApi

import com.nechaev.mycard.data.content.value.ValueRoot
import retrofit2.Call
import retrofit2.http.GET

interface ValueService {
    @GET("daily_json.js")
    fun getValueRoot() : Call<ValueRoot>
}