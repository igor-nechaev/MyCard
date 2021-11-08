package com.nechaev.mycard.data.network.value

import com.nechaev.mycard.data.model.value.ValueRoot
import retrofit2.Call
import retrofit2.http.GET

interface ValueService {
    @GET("daily_json.js")
    fun getValueRoot() : Call<ValueRoot>
}