package com.nechaev.mycard.model.valute.network

import com.nechaev.mycard.model.valute.entities.ValuteRoot
import retrofit2.Call
import retrofit2.http.GET

interface ValueService {

    @GET("daily_json.js")
    fun getValueRoot() : Call<ValuteRoot>

}