package com.nechaev.mycard.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nechaev.mycard.data.content.cardHolder.CardHolderList
import com.nechaev.mycard.data.content.cardHolder.ResourceCardHolders
import com.nechaev.mycard.data.network.Status
import com.nechaev.mycard.data.network.cardHolderApi.RetrofitUser
import com.nechaev.mycard.data.network.cardHolderApi.UsersService
import retrofit2.Call
import retrofit2.Response

object CardHolderRepository {
    private var cardHoldersService =
        RetrofitUser.getRetrofitUsers().create(UsersService::class.java)

    fun loadCardHolders(cardHoldersResource : MutableLiveData<ResourceCardHolders>){
        val call = cardHoldersService.getUserList()

        cardHoldersResource.postValue(ResourceCardHolders(Status.LOADING, null, null))

        call.enqueue(object : retrofit2.Callback<CardHolderList> {

            override fun onResponse(
                call: Call<CardHolderList>,
                response: Response<CardHolderList>) {
                if(response.body() != null) {
                    Log.d("cardHolderResponse", "onResponse \n" + response.body()!!.cardHolderList.toString())
                    Thread.sleep(2000) //TODO imitate loading
                    cardHoldersResource.postValue(ResourceCardHolders(Status.SUCCES, response.body()!!, null))
                }
            }

            override fun onFailure(call: Call<CardHolderList>, t: Throwable) {
                Log.d("cardHolderResponse", "onFailure   \n" + t.message)
                cardHoldersResource.postValue(ResourceCardHolders(Status.ERROR, null , null))
                }
            })

        }
}

