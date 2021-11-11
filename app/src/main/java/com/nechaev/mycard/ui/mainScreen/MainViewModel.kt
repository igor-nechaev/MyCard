package com.nechaev.mycard.ui.mainScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nechaev.mycard.data.network.Status
import com.nechaev.mycard.data.repository.CardHolderRepository
import com.nechaev.mycard.data.content.cardHolder.ResourceCardHolders

class MainViewModel : ViewModel() {
    var cardHoldersResource : MutableLiveData<ResourceCardHolders> = MutableLiveData()
    init {
        cardHoldersResource.postValue(ResourceCardHolders(Status.LOADING, null, null))
        loadCardHolders()
    }

    private fun loadCardHolders() {
        CardHolderRepository.loadCardHolders(cardHoldersResource)
    }
}