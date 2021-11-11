package com.nechaev.mycard.data.network.cardHolderApi

import com.nechaev.mycard.data.content.cardHolder.CardHolderList
import retrofit2.Call
import retrofit2.http.GET

interface UsersService {
    @GET("users.json")
    fun getUserList() : Call<CardHolderList>
}