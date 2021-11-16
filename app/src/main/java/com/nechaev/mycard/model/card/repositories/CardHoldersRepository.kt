package com.nechaev.mycard.model.card.repositories

import androidx.lifecycle.MutableLiveData
import com.nechaev.mycard.model.card.entities.CardHolderList
import com.nechaev.mycard.model.card.network.CardHoldersResource
import com.nechaev.mycard.model.card.network.CardHoldersService
import com.nechaev.mycard.model.card.network.RetrofitCardHolders
import com.nechaev.mycard.model.Status
import retrofit2.Call
import retrofit2.Response

object CardHoldersRepository {

    private var cardHoldersService =
        RetrofitCardHolders.getRetrofitUsers().create(CardHoldersService::class.java)

    fun loadCardHolders(cardHoldersResource : MutableLiveData<CardHoldersResource>){
        cardHoldersResource.postValue(CardHoldersResource(Status.LOADING, null, null))

        val call = cardHoldersService.getUserList()

        call.enqueue(object : retrofit2.Callback<CardHolderList> {

            override fun onResponse(call: Call<CardHolderList>, response: Response<CardHolderList>) {
                cardHoldersResource.postValue(CardHoldersResource(Status.SUCCES, response.body(), null))
            }

            override fun onFailure(call: Call<CardHolderList>, t: Throwable) {
                cardHoldersResource.postValue(CardHoldersResource(Status.ERROR, null , null))
            }


            })
        }
}

