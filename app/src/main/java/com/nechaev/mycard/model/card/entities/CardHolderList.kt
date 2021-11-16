package com.nechaev.mycard.model.card.entities

data class CardHolderList(val cardHolderList : List<CardHolder>) {
    companion object{
        const val serialized_name : String = "users"
    }
}