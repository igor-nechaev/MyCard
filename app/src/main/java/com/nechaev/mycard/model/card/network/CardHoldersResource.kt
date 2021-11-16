package com.nechaev.mycard.model.card.network

import com.nechaev.mycard.model.card.entities.CardHolderList
import com.nechaev.mycard.model.Status

data class CardHoldersResource(

    val status: Status,

    val data: CardHolderList?,

    val message: String?

    ) {}