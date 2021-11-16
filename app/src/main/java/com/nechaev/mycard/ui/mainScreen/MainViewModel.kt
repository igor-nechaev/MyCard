package com.nechaev.mycard.ui.mainScreen

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nechaev.mycard.model.card.repositories.CardHoldersRepository
import com.nechaev.mycard.model.card.network.CardHoldersResource
import com.nechaev.mycard.model.valute.network.ResourceValuteRoot
import com.nechaev.mycard.model.valute.repositories.ValuteRepository

class MainViewModel : ViewModel() {

    var cardHoldersResource : MutableLiveData<CardHoldersResource> = MutableLiveData()
    var valuteRootResource : MutableLiveData<ResourceValuteRoot> = MutableLiveData()

    init {
        loadCardHolders()
        loadValueRoot()
    }

    private fun loadCardHolders() {
        CardHoldersRepository.loadCardHolders(cardHoldersResource)
    }

    private fun loadValueRoot(){
        ValuteRepository.loadValueInfo(valuteRootResource)
    }

    fun reconnect() {
        loadCardHolders()
        loadValueRoot()
    }

}