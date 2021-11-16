package com.nechaev.mycard.model.card.network

import com.nechaev.mycard.model.card.entities.CardHolderList
import retrofit2.Call
import retrofit2.http.GET

interface CardHoldersService {

    @GET("users.json")
    fun getUserList() : Call<CardHolderList>

}